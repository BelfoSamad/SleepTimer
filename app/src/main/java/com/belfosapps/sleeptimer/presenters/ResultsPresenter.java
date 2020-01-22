package com.belfosapps.sleeptimer.presenters;

import com.belfosapps.sleeptimer.contracts.ResultsContract;
import com.belfosapps.sleeptimer.pojo.Time;
import com.belfosapps.sleeptimer.views.activities.ResultsActivity;

import java.util.ArrayList;

public class ResultsPresenter implements ResultsContract.Presenter{

    /***************************************** Declarations ***************************************/
    private ResultsActivity mView;

    /***************************************** Constructor ****************************************/
    public ResultsPresenter() {

    }

    /***************************************** Essential Methods **********************************/
    @Override
    public void attach(ResultsContract.View view) {
        mView = (ResultsActivity) view;
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
    public ArrayList<Time> calculateTime(String in) {
        return null;
    }

    @Override
    public void setAlarm() {

    }
}
