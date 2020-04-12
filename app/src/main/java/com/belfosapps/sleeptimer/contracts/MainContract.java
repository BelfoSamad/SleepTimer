package com.belfosapps.sleeptimer.contracts;

import com.belfosapps.sleeptimer.base.BasePresenter;
import com.belfosapps.sleeptimer.base.BaseView;
import com.belfosapps.sleeptimer.utils.Config;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;


public interface MainContract {

    interface Presenter extends BasePresenter<View> {

        void refreshConfig();

        void checkGDPRConsent();

        void loadAd(AdView ad);

        boolean isRTL(Locale locale);

        void calculateTime(String time, boolean isAlarm);

        void goToResults(ArrayList<String> times, boolean isAlarm);

        Config getConfig();
    }

    interface View extends BaseView {

        void initAdBanner();

        void initViewPager();

        void initTabLayout();

        void enableTabAt(int x);

    }

}
