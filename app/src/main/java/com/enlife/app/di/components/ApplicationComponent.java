package com.enlife.app.di.components;

import com.enlife.app.WakeUpReceiver;
import com.enlife.app.di.modules.AppModule;
import com.enlife.app.di.modules.DatabaseModule;
import com.enlife.app.di.modules.GoalsModule;
import com.enlife.app.di.modules.PresenterModule;
import com.enlife.app.di.modules.UtilityModule;
import com.enlife.app.screens.main.dialog.EventsBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalFragment;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneBottomDialog;
import com.enlife.app.screens.main.fragments.home.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        DatabaseModule.class,
        UtilityModule.class,
        PresenterModule.class,
        AppModule.class
})
public interface ApplicationComponent {

    void inject(WakeUpReceiver receiver);

    MainComponent.Builder mainComponentBuilder();

    GoalsComponent.Builder goalsComponentBuilder();

    EventsComponent.Builder eventsComponentBuilder();

    QuotesComponent.Builder quotesComponentBuilder();

    DailyScheduleComponent.Builder dailyScheduleBuilder();
}
