package com.belfosapps.sleeptimer.presenters;

import android.content.Intent;
import android.util.Log;

import com.belfosapps.sleeptimer.R;
import com.belfosapps.sleeptimer.contracts.MainContract;
import com.belfosapps.sleeptimer.models.SharedPreferencesHelper;
import com.belfosapps.sleeptimer.utils.Config;
import com.belfosapps.sleeptimer.utils.GDPR;
import com.belfosapps.sleeptimer.views.activities.MainActivity;
import com.belfosapps.sleeptimer.views.activities.ResultsActivity;
import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";
    /***************************************** Declarations ***************************************/
    private MainActivity mView;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private GDPR gdpr;
    private Config config;

    /***************************************** Constructor ****************************************/
    public MainPresenter(SharedPreferencesHelper sharedPreferencesHelper, Config config, GDPR gdpr) {
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
        if (mView.getResources().getBoolean(R.bool.GDPR_Enabled)) {
            gdpr.checkForConsent();
        }
    }

    @Override
    public void loadAd(AdView ad) {
        if (sharedPreferencesHelper.isAdPersonalized()) {
            gdpr.showPersonalizedAdBanner(ad);
        } else {
            gdpr.showNonPersonalizedAdBanner(ad);
        }
    }

    @Override
    public void calculateTime(String time, boolean isAlarm) {

        Log.d(TAG, "calculateTime: " + time);

        ArrayList<String> times = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm aa");
        Calendar cal = Calendar.getInstance();
        Date d;

        if (isAlarm) {
            try {
                d = df.parse(time);
                cal.setTime(d);
                cal.add(Calendar.MINUTE, 14);
                for (int i = 0; i < 6; i++) {
                    cal.add(Calendar.MINUTE, 90);
                    times.add(df.format(cal.getTime()));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                d = df.parse(time);
                cal.setTime(d);
                cal.add(Calendar.MINUTE, -180);
                for (int i = 0; i < 4; i++) {
                    cal.add(Calendar.MINUTE, -90);
                    Log.d(TAG, "calculateTime: " + df.format(cal.getTime()));
                    times.add(df.format(cal.getTime()));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        goToResults(times, isAlarm);
    }

    @Override
    public void goToResults(ArrayList<String> times, boolean isAlarm) {
        Intent results = new Intent(mView, ResultsActivity.class);
        Log.d(TAG, "goToResults: " + times.size());
        results.putStringArrayListExtra("times", times);
        results.putExtra("alarm", isAlarm);
        mView.startActivity(results);
    }

    @Override
    public Config getConfig() {
        return config;
    }
}
