package com.belfosapps.sleeptimer.views.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.belfosapps.sleeptimer.R;
import com.belfosapps.sleeptimer.presenters.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class WakeupFragment extends Fragment {

    /****************************************** Declarations **************************************/
    private MainPresenter mPresenter;
    private String[] am_pm = {"AM", "PM"};

    /****************************************** View Declarations *********************************/
    @BindView(R.id.hours)
    NumberPicker hoursPicker;
    @BindView(R.id.minutes)
    NumberPicker minutesPicker;
    @BindView(R.id.am_pm)
    NumberPicker am_pmPicker;

    /**************************************** Click Listeners *************************************/
    @OnClick(R.id.wake_up_calculate)
    public void calculate() {
        String time = hoursPicker.getValue() + ":" + minutesPicker.getValue() + " " + am_pm[am_pmPicker.getValue()];
        mPresenter.calculateTime(time, false);
    }

    /****************************************** Constructor ***************************************/
    public WakeupFragment() {
        // Required empty public constructor
    }

    public WakeupFragment(MainPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    /****************************************** Essential Methods *********************************/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wakeup, container, false);

        //Init ButterKnife
        ButterKnife.bind(this, view);

        initUI();

        return view;
    }

    /****************************************** Methods *******************************************/
    private void initUI() {
        hoursPicker.setMinValue(1);
        hoursPicker.setMaxValue(12);
        minutesPicker.setMinValue(0);
        minutesPicker.setMaxValue(59);
        am_pmPicker.setMinValue(0);
        am_pmPicker.setMaxValue(1);


        am_pmPicker.setDisplayedValues(am_pm);
    }

}
