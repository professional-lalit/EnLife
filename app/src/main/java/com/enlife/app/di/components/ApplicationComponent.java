package com.enlife.app.di.components;

import com.enlife.app.di.modules.DatabaseModule;
import com.enlife.app.di.modules.GoalsModule;
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
        UtilityModule.class
})
public interface ApplicationComponent {

    void inject(EventsBottomDialog dialog);

    void inject(HomeFragment dialog);


    GoalsComponent.Builder goalsComponentBuilder();

    EventsComponent.Builder eventsComponentBuilder();
}
