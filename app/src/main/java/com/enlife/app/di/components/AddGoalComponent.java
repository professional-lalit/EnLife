package com.enlife.app.di.components;

import com.enlife.app.di.modules.GoalsModule;
import com.enlife.app.di.scopes.FragmentScope;
import com.enlife.app.screens.main.fragments.goals.addgoal.AddGoalFragment;
import com.enlife.app.screens.main.fragments.goals.addmilestone.AddMilestoneBottomDialog;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {GoalsModule.class})
public interface AddGoalComponent {

    void inject(AddGoalFragment addGoalFragment);

    @Subcomponent.Factory
    interface Factory {
        AddGoalComponent create(@BindsInstance AddGoalFragment addGoalFragment);
    }

}
