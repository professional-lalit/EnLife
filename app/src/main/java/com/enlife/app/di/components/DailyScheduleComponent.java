package com.enlife.app.di.components;

import com.enlife.app.screens.main.fragments.schedule.DailyScheduleFragment;

import dagger.Subcomponent;

@Subcomponent
public interface DailyScheduleComponent {

    void inject(DailyScheduleFragment dailyScheduleFragment);

    @Subcomponent.Builder
    interface Builder {
        DailyScheduleComponent build();
    }
}
