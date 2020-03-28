package com.belfosapps.sleeptimer.contracts;

import com.belfosapps.sleeptimer.base.BasePresenter;
import com.belfosapps.sleeptimer.base.BaseView;
import com.belfosapps.sleeptimer.utils.Config;

import java.util.ArrayList;


public interface MainContract {

    interface Presenter extends BasePresenter<View> {

        void refreshConfig();

        void checkGDPRConsent();

        //void loadAd(AdView ad);

        void calculateTime(String time, boolean isAlarm);

        void goToResults(ArrayList<String> times, boolean isAlarm);

        Config getConfig();
    }

    interface View extends BaseView {

        void initViewPager();

        void initTabLayout();

        void enableTabAt(int x);

    }

}
