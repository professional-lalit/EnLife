package com.enlife.app.screens.main.fragments.goals.addmilestone;

import com.enlife.app.di.modules.GoalsModule;
import com.enlife.app.di.scopes.FragmentScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {GoalsModule.class})
public interface AddMilestoneComponent {

    void inject(AddMilestoneBottomDialog dialog);

    @Subcomponent.Factory
    interface Factory {
        AddMilestoneComponent create(@BindsInstance AddMilestoneBottomDialog dialog);
    }
}
