package com.enlife.app.di.modules;

import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalEventDataOperator;
import com.enlife.app.di.scopes.FragmentScope;
import com.enlife.app.screens.main.fragments.schedule.DailyScheduleDialog;
import com.enlife.app.screens.main.fragments.schedule.DailySchedulePresenter;
import com.enlife.app.utils.DateFormatter;

import dagger.Module;
import dagger.Provides;

@Module
public class DailyScheduleModule {

    @FragmentScope
    @Provides
    DailySchedulePresenter dailySchedulePresenter(GoalEventDataOperator goalEventDataOperator,
                                                  DateFormatter dateFormatter,
                                                  EventDataOperator eventDataOperator,
                                                  DailyScheduleDialog dialog
    ) {
        return new DailySchedulePresenter(goalEventDataOperator, eventDataOperator, dateFormatter, dialog);
    }


}
