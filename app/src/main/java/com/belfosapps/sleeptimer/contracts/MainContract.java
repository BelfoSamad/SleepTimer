package com.belfosapps.sleeptimer.contracts;

import com.belfosapps.sleeptimer.base.BasePresenter;
import com.belfosapps.sleeptimer.base.BaseView;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public interface MainContract {

    interface Presenter extends BasePresenter<View> {

        void checkGDPRConsent();

        void requestGDPR();

        void loadAd(AdView ad);

        void calculateTime(String time, boolean isAlarm);

        void goToResults(ArrayList<String> times, boolean isAlarm);
    }

    interface View extends BaseView {

        void initAdBanner();

        void initViewPager();

        void initTabLayout();

        void enableTabAt(int x);
    }

}
