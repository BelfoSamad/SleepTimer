package com.belfosapps.sleeptimer.utils;

import android.content.SharedPreferences;

public class Config {
    private static final String GDPR = "Gdpr";
    private static final String BANNER = "Banner";
    private static final String PUBLISHER = "Publisher";
    private static final String BANNER_ID = "Banner ID";

    private SharedPreferences preferences;

    public Config(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public Config() {
    }
}
