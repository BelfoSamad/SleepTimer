package com.belfosapps.sleeptimer.views.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.belfosapps.sleeptimer.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WakeupFragment extends Fragment {

    /****************************************** Declarations **************************************/

    /****************************************** View Declarations *********************************/

    /**************************************** Click Listeners *************************************/

    /****************************************** Constructor ***************************************/
    public WakeupFragment() {
        // Required empty public constructor
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
    }

}
