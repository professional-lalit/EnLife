package com.enlife.app.di.components;

import com.enlife.app.screens.main.fragments.schedule.DailyScheduleDialog;

import dagger.Subcomponent;

@Subcomponent
public interface DailyScheduleComponent {

    void inject(DailyScheduleDialog dailyScheduleDialog);

    @Subcomponent.Builder
    interface Builder {
        DailyScheduleComponent build();
    }
}
