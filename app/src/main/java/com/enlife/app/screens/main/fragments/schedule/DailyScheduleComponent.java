package com.enlife.app.screens.main.fragments.schedule;

import com.enlife.app.di.modules.DailyScheduleModule;
import com.enlife.app.di.scopes.FragmentScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {DailyScheduleModule.class})
public interface DailyScheduleComponent {

    void inject(DailyScheduleDialog dailyScheduleDialog);

    @Subcomponent.Factory
    interface Factory {
        DailyScheduleComponent create(@BindsInstance DailyScheduleDialog dailyScheduleDialog);
    }
}
