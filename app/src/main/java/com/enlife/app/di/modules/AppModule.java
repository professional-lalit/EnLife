package com.enlife.app.di.modules;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.common.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    CustomApplication customApplication() {
        return CustomApplication.getInstance();
    }

    @Singleton
    @Provides
    PreferenceManager preferenceManager(CustomApplication customApplication) {
        return new PreferenceManager(customApplication.sharedPreferences);
    }

}
