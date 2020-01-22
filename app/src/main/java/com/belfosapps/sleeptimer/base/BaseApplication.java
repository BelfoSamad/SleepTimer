package com.belfosapps.sleeptimer.base;

import androidx.multidex.MultiDexApplication;
import com.belfosapps.sleeptimer.di.components.ApplicationComponent;
import com.belfosapps.sleeptimer.di.components.DaggerApplicationComponent;
import com.belfosapps.sleeptimer.di.modules.ApplicationModule;


public class BaseApplication extends MultiDexApplication {
    ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize Dagger For Application
        appComponent = getComponent();

        //Inject the Component Here
        appComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerApplicationComponent
                    .builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return appComponent;
    }
}
