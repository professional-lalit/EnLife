package com.enlife.app.di;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.operators.EventDataOperator;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    public EventDataOperator eventDataOperator(CustomApplication customApplication) {
        return new EventDataOperator(customApplication.getApplicationContext());
    }

}
