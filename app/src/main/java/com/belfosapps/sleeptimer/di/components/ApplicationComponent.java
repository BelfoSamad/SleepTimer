package com.belfosapps.sleeptimer.di.components;

import android.content.Context;

import com.belfosapps.sleeptimer.base.BaseApplication;
import com.belfosapps.sleeptimer.di.annotations.ApplicationContext;
import com.belfosapps.sleeptimer.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**********************************
 © 2018 Sam Dev
 ALL RIGHTS RESERVED
 ***********************************/

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseApplication baseApplication);

    /*
        Put Here All the Dependencies The Application Provides
     */

    //Context
    @ApplicationContext
    Context getApplicationContext();
}
