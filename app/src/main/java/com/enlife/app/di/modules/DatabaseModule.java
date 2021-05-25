package com.enlife.app.di.modules;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalDataOperator;
import com.enlife.app.database.operators.GoalEventDataOperator;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    public EventDataOperator eventDataOperator(CustomApplication customApplication) {
        return new EventDataOperator(customApplication.getApplicationContext());
    }

    @Provides
    public GoalDataOperator goalDataOperator(CustomApplication customApplication) {
        return new GoalDataOperator(customApplication.getApplicationContext());
    }

    @Provides
    public GoalEventDataOperator goalEventDataOperator(CustomApplication customApplication) {
        return new GoalEventDataOperator(customApplication.getApplicationContext());
    }

}
