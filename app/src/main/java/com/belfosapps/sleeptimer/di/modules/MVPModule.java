package com.belfosapps.sleeptimer.di.modules;

import android.app.Activity;
import android.content.Context;


import com.belfosapps.sleeptimer.di.annotations.ActivityContext;
import com.belfosapps.sleeptimer.models.SharedPreferencesHelper;
import com.belfosapps.sleeptimer.presenters.MainPresenter;
import com.belfosapps.sleeptimer.utils.GDPR;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MVPModule {

    private Activity mActivity;

    //Constructor
    public MVPModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    //Context
    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    //Presenters
    @Provides
    @Singleton
    MainPresenter providesMainPresenter(SharedPreferencesHelper sharedPreferencesHelper,
                                        GDPR gdpr) {
        return new MainPresenter(sharedPreferencesHelper, gdpr);
    }
}
