package com.enlife.app.di.modules;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalDataOperator;
import com.enlife.app.database.operators.MilestoneDataOperator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    public EventDataOperator eventDataOperator(CustomApplication customApplication) {
        return new EventDataOperator(customApplication.getApplicationContext());
    }

    @Singleton
    @Provides
    public GoalDataOperator goalDataOperator(CustomApplication customApplication) {
        return new GoalDataOperator(customApplication.getApplicationContext());
    }

    @Singleton
    @Provides
    public MilestoneDataOperator milestoneDataOperator(CustomApplication customApplication) {
        return new MilestoneDataOperator(customApplication.getApplicationContext());
    }

}
