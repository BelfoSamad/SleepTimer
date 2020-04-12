package com.belfosapps.sleeptimer.models;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesHelper {
    private static final String NOTES = "Notes";
    private static final String DARK_MODE = "Dark Mode";
    private static final String PERSONALIZED_ADS = "AD";

    /************************************* Declarations *******************************************/
    private SharedPreferences sharedPref;
    private Gson gson;

    /************************************* Constructor ********************************************/
    public SharedPreferencesHelper(SharedPreferences sharedPref, Gson gson) {
        this.sharedPref = sharedPref;
        this.gson = gson;
    }

    /************************************* Methods ***********************************************/

    private boolean isEmpty(String mode) {
        return sharedPref.getString(mode, null) == null;
    }


    /************************************* Extra Methods ******************************************/
    //Ad Personalization
    public void setAdPersonalized(boolean isPersonalized) {
        SharedPreferences.Editor editor;
        editor = sharedPref.edit();
        editor.putBoolean(PERSONALIZED_ADS, isPersonalized).apply();
    }

    public boolean isAdPersonalized() {
        return sharedPref.getBoolean(PERSONALIZED_ADS, false);
    }
}
