package com.enlife.app.common;

import android.app.Application;
import android.content.Context;

import com.enlife.app.database.DatabaseOperator;

public class CustomApplication extends Application {

    private static CustomApplication customApplication;
    private static DatabaseOperator databaseOperator;

    public static CustomApplication getInstance() {
        return customApplication;
    }

    public static Context getAppContext() {
        return customApplication.getApplicationContext();
    }

    public static DatabaseOperator getDatabaseOperator() {
        return databaseOperator;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        customApplication = this;
        databaseOperator = new DatabaseOperator();
    }
}
