package com.belfosapps.sleeptimer.views.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

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
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**************************************** Declarations ****************************************/
    private MVPComponent mvpComponent;
    @Inject
    MainPresenter mPresenter;
    private MainPagerAdapter mAdapter;

    /**************************************** View Declarations ***********************************/
    //View Pages
    @BindView(R.id.view_pager)
    ViewPager2 mViewPager;
    @BindView(R.id.tab_layout_main)
    TabLayout mTab;
    @BindView(R.id.adView_main)
    AdView ad;

    /**************************************** Essential Methods ***********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Set Theme
        setTheme(R.style.AppTheme);

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

        //Init Ad Banner
        initAdBanner();
        //Init View Pager
        initViewPager();

        //Check For Consent
        mPresenter.checkGDPRConsent();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ad.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ad.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ad.resume();
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
    @Override
    public void initAdBanner() {
        if (getResources().getBoolean(R.bool.AD_BANNER_Enabled)) {
            ad.setAdUnitId(getResources().getString(R.string.ADMOB_BANNER_ID));
            ad.setAdSize(AdSize.SMART_BANNER);
            mPresenter.loadAd(ad);
        } else {
            ad.setVisibility(GONE);
        }
    }

    @Override
    public void initViewPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        WakeupFragment wakeup = new WakeupFragment(mPresenter);
        SleepFragment sleep = new SleepFragment(mPresenter);

        fragments.add(wakeup);
        fragments.add(sleep);

        mAdapter = new MainPagerAdapter(this, fragments, MainActivity.this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);

        initTabLayout();
    }

    @Override
    public void initTabLayout() {
        new TabLayoutMediator(mTab, mViewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < mTab.getTabCount(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            assert tab != null;
            tab.setCustomView(mAdapter.getTabView(i));
        }
        enableTabAt(0);

        //TabLayout Listener
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                enableTabAt(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void enableTabAt(int x) {
        TabLayout.Tab tab = mTab.getTabAt(x);

        for (int i = 0; i < getResources().getStringArray(R.array.tab_titles).length; i++) {
            if (i == x) {
                assert tab != null;
                Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.featured_tab_icon).setVisibility(View.VISIBLE);

            } else {
                TabLayout.Tab tab1 = mTab.getTabAt(i);
                assert tab1 != null;
                Objects.requireNonNull(tab1.getCustomView()).findViewById(R.id.featured_tab_icon).setVisibility(GONE);
            }
        }
    }
}
