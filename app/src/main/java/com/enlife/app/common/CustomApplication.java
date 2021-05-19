package com.enlife.app.common;

import android.app.Application;
import android.content.Context;

import com.enlife.app.di.ApplicationComponent;
import com.enlife.app.di.DaggerApplicationComponent;

public class CustomApplication extends Application {

    private static CustomApplication customApplication;

    public static CustomApplication getInstance() {
        return customApplication;
    }

    public static Context getAppContext() {
        return customApplication.getApplicationContext();
    }

    public ApplicationComponent applicationComponent = DaggerApplicationComponent.create();

    @Override
    public void onCreate() {
        super.onCreate();
        customApplication = this;
    }
}
