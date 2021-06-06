package com.enlife.app.di.modules;

import com.enlife.app.database.operators.GoalEventDataOperator;
import com.enlife.app.di.scopes.PerScreen;
import com.enlife.app.screens.main.fragments.schedule.DailySchedulePresenter;
import com.enlife.app.utils.DateFormatter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    DailySchedulePresenter dailySchedulePresenter(GoalEventDataOperator goalEventDataOperator, DateFormatter dateFormatter) {
        return new DailySchedulePresenter(goalEventDataOperator, dateFormatter);
    }

}
