package com.belfosapps.sleeptimer.di.components;


import android.content.Context;
import com.belfosapps.sleeptimer.views.activities.MainActivity;
import com.belfosapps.sleeptimer.di.annotations.ActivityContext;
import com.belfosapps.sleeptimer.di.modules.ApplicationModule;
import com.belfosapps.sleeptimer.di.modules.MVPModule;
import com.belfosapps.sleeptimer.views.activities.ResultsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MVPModule.class})
public interface MVPComponent {

    //Inject in Activities
    void inject(MainActivity mainActivity);

    //Context
    @ActivityContext
    Context getActivityContext();
}
