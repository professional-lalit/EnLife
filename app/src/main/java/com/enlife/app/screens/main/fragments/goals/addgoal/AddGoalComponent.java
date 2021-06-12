package com.enlife.app.screens.main.fragments.goals.addgoal;

import com.enlife.app.di.modules.GoalsModule;
import com.enlife.app.di.scopes.FragmentScope;

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
