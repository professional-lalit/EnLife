package com.enlife.app.common;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.enlife.app.di.components.ApplicationComponent;
import com.enlife.app.di.components.DaggerApplicationComponent;
import com.enlife.app.di.modules.DatabaseModule;
import com.enlife.app.di.modules.UtilityModule;

public class CustomApplication extends Application {

    private static CustomApplication customApplication;

    public static CustomApplication getInstance() {
        return customApplication;
    }

    public static Context getAppContext() {
        return customApplication.getApplicationContext();
    }

    public ApplicationComponent applicationComponent;

    public SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(PreferenceManager.PREF_NAME, Context.MODE_PRIVATE);
        customApplication = this;
        applicationComponent = DaggerApplicationComponent
                .builder()
                .utilityModule(new UtilityModule())
                .databaseModule(new DatabaseModule())
                .build();
    }
}
