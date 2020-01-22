package com.belfosapps.sleeptimer.presenters;

import com.belfosapps.sleeptimer.contracts.MainContract;
import com.belfosapps.sleeptimer.models.SharedPreferencesHelper;
import com.belfosapps.sleeptimer.utils.Config;
import com.belfosapps.sleeptimer.utils.GDPR;
import com.belfosapps.sleeptimer.views.activities.MainActivity;

public class MainPresenter implements MainContract.Presenter {

    /***************************************** Declarations ***************************************/
    private MainActivity mView;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private GDPR gdpr;
    private Config config;

    /***************************************** Constructor ****************************************/
    public MainPresenter(SharedPreferencesHelper sharedPreferencesHelper,Config config, GDPR gdpr) {
        this.sharedPreferencesHelper = sharedPreferencesHelper;
        this.config = config;
        this.gdpr = gdpr;
    }

    /***************************************** Essential Methods **********************************/
    @Override
    public void attach(MainContract.View view) {
        mView = (MainActivity) view;
    }

    @Override
    public void dettach() {
        mView = null;
    }

    @Override
    public boolean isAttached() {
        return !(mView == null);
    }


    /***************************************** Methods ********************************************/
    @Override
    public void refreshConfig() {

    }

    @Override
    public void checkGDPRConsent() {

    }

    @Override
    public void getTime(String time) {

    }

    @Override
    public void goToResults() {

    }

    @Override
    public Config getConfig() {
        return null;
    }
}
