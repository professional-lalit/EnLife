package com.enlife.app.di.modules;

import android.content.Context;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.utils.DateFormatter;
import com.enlife.app.utils.Utils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilityModule {

    @Singleton
    @Provides
    public Context context() {
        return CustomApplication.getAppContext();
    }

    @Singleton
    @Provides
    public Utils utils() {
        return new Utils();
    }

    @Singleton
    @Provides
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }
}
