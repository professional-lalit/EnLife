package com.enlife.app.di.modules;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalDataOperator;
import com.enlife.app.database.operators.GoalEventDataOperator;
import com.enlife.app.database.operators.MilestoneDataOperator;
import com.enlife.app.database.operators.QuoteDataOperator;

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
    public GoalEventDataOperator goalEventDataOperator(CustomApplication customApplication) {
        return new GoalEventDataOperator(customApplication.getApplicationContext());
    }


    @Singleton
    @Provides
    public MilestoneDataOperator milestoneDataOperator(CustomApplication customApplication) {
        return new MilestoneDataOperator(customApplication.getApplicationContext());
    }

    @Singleton
    @Provides
    public QuoteDataOperator quoteDataOperator(CustomApplication customApplication) {
        return new QuoteDataOperator(customApplication.getApplicationContext());
    }


}
