package com.enlife.app.di.components;

import com.enlife.app.di.modules.DatabaseModule;
import com.enlife.app.di.modules.GoalManagementModule;
import com.enlife.app.di.modules.UtilityModule;
import com.enlife.app.screens.main.dialog.EventsBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addgoal.GoalManagementFragment;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneBottomDialog;
import com.enlife.app.screens.main.fragments.home.HomeFragment;

import dagger.Component;

@Component(modules = {
        DatabaseModule.class,
        UtilityModule.class,
        GoalManagementModule.class
})
public interface ApplicationComponent {

    void inject(EventsBottomDialog dialog);

    void inject(AddEventBottomDialog dialog);

    void inject(GoalManagementFragment dialog);

    void inject(AddMilestoneBottomDialog dialog);

    void inject(HomeFragment dialog);
}
