package com.enlife.app.common;

import android.app.Application;
import android.content.Context;

import com.enlife.app.database.operators.DatabaseOperator;

public class CustomApplication extends Application {

    private static CustomApplication customApplication;

    public static CustomApplication getInstance() {
        return customApplication;
    }

    public static Context getAppContext() {
        return customApplication.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        customApplication = this;
    }
}
