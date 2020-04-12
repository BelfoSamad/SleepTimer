package com.belfosapps.sleeptimer.views.activities;

import android.app.AlarmManager;
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
import com.belfosapps.sleeptimer.contracts.ResultsContract;
import com.belfosapps.sleeptimer.di.components.DaggerMVPComponent;
import com.belfosapps.sleeptimer.di.components.MVPComponent;
import com.belfosapps.sleeptimer.di.modules.ApplicationModule;
import com.belfosapps.sleeptimer.di.modules.MVPModule;
import com.belfosapps.sleeptimer.pojo.Time;
import com.belfosapps.sleeptimer.presenters.ResultsPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultsActivity extends AppCompatActivity implements ResultsContract.View {

    private static final String TAG = "ResultsActivity";
    /**************************************** Declarations ****************************************/
    private MVPComponent mvpComponent;
    @Inject
    ResultsPresenter mPresenter;
    private ArrayList<String> times;

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
        onBackPressed();
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

}
