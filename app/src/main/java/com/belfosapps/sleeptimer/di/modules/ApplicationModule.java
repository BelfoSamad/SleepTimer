package com.belfosapps.sleeptimer.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.belfosapps.sleeptimer.di.annotations.ApplicationContext;
import com.belfosapps.sleeptimer.models.SharedPreferencesHelper;
import com.belfosapps.sleeptimer.utils.Config;
import com.belfosapps.sleeptimer.utils.GDPR;
import com.google.ads.consent.ConsentForm;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**********************************
 © 2018 Sam Dev
 ALL RIGHTS RESERVED
 ***********************************/

@Module
public class ApplicationModule {

    //Declarations
    private final Application mApplication;
    private ConsentForm form;

    //Constructor
    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    /*
        Here We Provide The Dependencies
     */

    //Context
    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    /*
        Models
     */

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("BASICS", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPreferencesHelper provideSharedPrefsHelper(SharedPreferences sharedPreferences, Gson gson) {
        return new SharedPreferencesHelper(sharedPreferences, gson);
    }

    /*
        Utils
     */
    @Provides
    @Singleton
    Config providesConfig(){
        SharedPreferences preferences = mApplication.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
        return new Config(preferences);
    }

    @Provides
    @Singleton
    GDPR providesGDPR(SharedPreferencesHelper sharedPreferencesHelper, Config config) {
        return new GDPR(sharedPreferencesHelper,form, mApplication, config);
    }
}
