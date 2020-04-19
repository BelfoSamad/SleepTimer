package com.belfosapps.sleeptimer.views.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.belfosapps.sleeptimer.R;
import com.belfosapps.sleeptimer.presenters.MainPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SleepFragment extends Fragment {
    private static final String TAG = "SleepFragment";
    /****************************************** Declarations **************************************/
    private MainPresenter mPresenter;

    /**************************************** Click Listeners *************************************/
    @OnClick(R.id.sleep_calculate)
    void calculate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        mPresenter.calculateTime(sdf.format(new Date()), true);
    }

    /****************************************** Constructor ***************************************/
    public SleepFragment() {
        // Required empty public constructor
    }

    public SleepFragment(MainPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    /****************************************** Essential Methods *********************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);

        //Init ButterKnife
        ButterKnife.bind(this, view);

        return view;
    }

}
