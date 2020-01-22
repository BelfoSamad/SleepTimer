package com.belfosapps.sleeptimer.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.belfosapps.sleeptimer.R;
import com.belfosapps.sleeptimer.contracts.MainContract;
import com.belfosapps.sleeptimer.di.components.DaggerMVPComponent;
import com.belfosapps.sleeptimer.di.components.MVPComponent;
import com.belfosapps.sleeptimer.di.modules.ApplicationModule;
import com.belfosapps.sleeptimer.di.modules.MVPModule;
import com.belfosapps.sleeptimer.presenters.MainPresenter;
import com.belfosapps.sleeptimer.views.adapters.MainPagerAdapter;
import com.belfosapps.sleeptimer.views.fragments.SleepFragment;
import com.belfosapps.sleeptimer.views.fragments.WakeupFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**************************************** Declarations ****************************************/
    private MVPComponent mvpComponent;
    @Inject
    MainPresenter mPresenter;
    private MainPagerAdapter mAdapter;
    private WakeupFragment wakeup;
    private SleepFragment sleep;

    /**************************************** View Declarations ***********************************/
    //View Pages
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout_main)
    TabLayout mTab;

    /**************************************** Click Listeners *************************************/

    /**************************************** Essential Methods ***********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Dagger For Application
        mvpComponent = getComponent();
        //Inject the Component Here
        mvpComponent.inject(this);

        //Set ButterKnife
        ButterKnife.bind(this);

        //Attach View To Presenter
        mPresenter.attach(this);

        //Init UI
        initUI();
    }

    @Override
    public MVPComponent getComponent() {
        if (mvpComponent == null) {
            mvpComponent = DaggerMVPComponent
                    .builder()
                    .applicationModule(new ApplicationModule(getApplication()))
                    .mVPModule(new MVPModule(this))
                    .build();
        }
        return mvpComponent;
    }

    /**************************************** Methods *********************************************/
    private void initUI() {
        initViewPager();
        initTabLayout();
    }

    @Override
    public void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        wakeup = new WakeupFragment();
        sleep = new SleepFragment();

        fragments.add(wakeup);
        fragments.add(sleep);

        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments, MainActivity.this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(0);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initTabLayout() {

    }

    @Override
    public void enableTabAt(int x) {
        TabLayout.Tab tab = mTab.getTabAt(x);

        for (int i = 0; i < 3; i++) {
            if (i == x) {
                assert tab != null;
                tab.getCustomView().findViewById(R.id.tab_index).setVisibility(VISIBLE);

            } else {
                TabLayout.Tab tab1 = mTab.getTabAt(i);
                assert tab1 != null;
                tab.getCustomView().findViewById(R.id.tab_index).setVisibility(GONE);
            }
        }
    }
}
