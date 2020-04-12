package com.belfosapps.sleeptimer.contracts;

import androidx.recyclerview.widget.RecyclerView;

import com.belfosapps.sleeptimer.base.BasePresenter;
import com.belfosapps.sleeptimer.base.BaseView;
import com.belfosapps.sleeptimer.pojo.Time;

import java.util.ArrayList;

public interface ResultsContract {


    interface Presenter extends BasePresenter<ResultsContract.View> {

        ArrayList<Time> calculateTime(String in);

        void setAlarm();

    }

    interface View extends BaseView {


    }

}
