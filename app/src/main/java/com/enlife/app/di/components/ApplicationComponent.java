package com.enlife.app.di.components;

import com.enlife.app.WakeUpReceiver;
import com.enlife.app.di.modules.AppModule;
import com.enlife.app.di.modules.DatabaseModule;
import com.enlife.app.di.modules.UtilityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        DatabaseModule.class,
        UtilityModule.class,
        AppModule.class
})
public interface ApplicationComponent {

    void inject(WakeUpReceiver receiver);

    MainComponent.Factory mainComponentBuilder();

    AddGoalComponent.Factory addGoalComponentBuilder();

    AddMilestoneComponent.Factory addMilestoneComponentBuilder();

    AddEventComponent.Factory eventsComponentBuilder();

    HomeFragmentComponent.Factory homeFragmentComponentBuilder();

    EventsBottomDialogComponent.Factory eventsBottomDialogComponentBuilder();

    QuotesComponent.Factory quotesComponentBuilder();

    DailyScheduleComponent.Factory dailyScheduleBuilder();
}
