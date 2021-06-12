package com.enlife.app.common;

import com.enlife.app.WakeUpReceiver;
import com.enlife.app.di.modules.AppModule;
import com.enlife.app.di.modules.DatabaseModule;
import com.enlife.app.di.modules.UtilityModule;
import com.enlife.app.screens.main.MainComponent;
import com.enlife.app.screens.main.dialog.EventsBottomDialogComponent;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventComponent;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalComponent;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneComponent;
import com.enlife.app.screens.main.fragments.home.HomeFragmentComponent;
import com.enlife.app.screens.main.fragments.schedule.DailyScheduleComponent;
import com.enlife.app.screens.main.fragments.stoicism.QuotesComponent;

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
