package com.belfosapps.sleeptimer.views.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.belfosapps.sleeptimer.R;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentPagerAdapter {

    /*************************************** Declarations *****************************************/
    private ArrayList<Fragment> fragments;
    private Context context;

    /*************************************** Constructor ******************************************/
    public MainPagerAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    /*************************************** Methods **********************************************/
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public View getTabView(int position) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = view.findViewById(R.id.tab_title);
        tv.setText(context.getResources().getStringArray(R.array.tab_titles)[position]);
        return view;
    }
}
