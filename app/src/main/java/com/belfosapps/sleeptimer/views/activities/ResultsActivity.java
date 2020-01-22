package com.belfosapps.sleeptimer.views.activities;

import android.os.Bundle;

import com.belfosapps.sleeptimer.contracts.ResultsContract;
import com.belfosapps.sleeptimer.di.components.DaggerMVPComponent;
import com.belfosapps.sleeptimer.di.components.MVPComponent;
import com.belfosapps.sleeptimer.di.modules.ApplicationModule;
import com.belfosapps.sleeptimer.di.modules.MVPModule;
import com.belfosapps.sleeptimer.pojo.Time;
import com.belfosapps.sleeptimer.presenters.ResultsPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.belfosapps.sleeptimer.R;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity implements ResultsContract.View {

    /**************************************** Declarations ****************************************/
    private MVPComponent mvpComponent;
    @Inject
    ResultsPresenter mPresenter;

    /**************************************** View Declarations ***********************************/

    /**************************************** Click Listeners *************************************/

    /**************************************** Essential Methods ***********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

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
    private void initUI() { }

    @Override
    public void initRecyclerView(ArrayList<Time> times) {

    }

    @Override
    public void updateRecyclerView() {

    }
}
