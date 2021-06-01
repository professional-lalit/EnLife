package com.enlife.app.di.components;

import com.enlife.app.di.modules.GoalsModule;
import com.enlife.app.di.scopes.PerScreen;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalFragment;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneBottomDialog;

import dagger.Component;
import dagger.Subcomponent;

@Subcomponent(modules = {GoalsModule.class})
@PerScreen
public interface GoalsComponent {

    void inject(AddMilestoneBottomDialog dialog);

    void inject(AddGoalFragment addGoalFragment);

    @Subcomponent.Builder
    interface Builder {
        GoalsComponent build();
    }
}
