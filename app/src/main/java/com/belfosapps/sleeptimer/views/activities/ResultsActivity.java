package com.belfosapps.sleeptimer.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.belfosapps.sleeptimer.R;
import com.belfosapps.sleeptimer.models.SharedPreferencesHelper;
import com.belfosapps.sleeptimer.utils.GDPR;
import com.google.ads.consent.ConsentForm;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";
    /**************************************** Declarations ****************************************/
    private ArrayList<String> times;
    private InterstitialAd mInterstitialAd;

    /**************************************** View Declarations ***********************************/
    @BindView(R.id.indication)
    TextView indication;
    @BindView(R.id.times)
    NumberPicker mWheel;
    @BindView(R.id.set_alarm)
    Button set_alarm;

    /**************************************** Click Listeners *************************************/
    @OnClick(R.id.back)
    public void goBack() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.set_alarm)
    public void setAlarm() {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR,
                Integer.parseInt(times.get(mWheel.getValue()).split(":")[0]));
        intent.putExtra(AlarmClock.EXTRA_MINUTES,
                Integer.parseInt(times.get(mWheel.getValue()).split(":")[1].split(" ")[0]));
        if (times.get(mWheel.getValue()).split(":")[1].split(" ")[1].equals("AM"))
            intent.putExtra(AlarmClock.EXTRA_IS_PM, false);
        else intent.putExtra(AlarmClock.EXTRA_IS_PM, true);

        startActivity(intent);
    }

    /**************************************** Essential Methods ***********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Set ButterKnife
        ButterKnife.bind(this);

        //Init UI
        initUI();

        //Init Interstitial Ad
        initInterstitialAd();
    }

    /**************************************** Methods *********************************************/
    private void initUI() {
        if (getIntent().getBooleanExtra("alarm", false)) {
            set_alarm.setVisibility(View.VISIBLE);
            indication.setText(R.string.sleep_result_text);
        } else {
            set_alarm.setVisibility(View.INVISIBLE);
            indication.setText(R.string.wakeup_result_text);
        }

        times = getIntent().getStringArrayListExtra("times");
        mWheel.setMinValue(0);
        mWheel.setMaxValue(times.size() - 1);
        mWheel.setDisplayedValues(times.toArray(new String[times.size()]));
    }

    private void initInterstitialAd() {
        SharedPreferencesHelper sharedPrefs = new SharedPreferencesHelper(getSharedPreferences("BASICS",
                Context.MODE_PRIVATE), new Gson());
        GDPR gdpr = new GDPR(sharedPrefs, this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.INTERSTITIAL_AD_ID));

        if (getResources().getBoolean(R.bool.INTERSTITIAL_AD_Enabled)) {
            if (sharedPrefs.isAdPersonalized())
                gdpr.loadPersonalizedInterstitialAd(mInterstitialAd);
            else gdpr.loadNonPersonalizedInterstitialAd(mInterstitialAd);

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    // Code to be executed when an ad request fails.
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                }

                @Override
                public void onAdClosed() {
                    onBackPressed();
                }
            });
        }
    }

}
