package com.enlife.app.di.components;

import com.enlife.app.di.modules.EventsModule;
import com.enlife.app.di.scopes.FragmentScope;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {EventsModule.class})
public interface AddEventComponent {

    void inject(AddEventBottomDialog dialog);

    @Subcomponent.Factory
    interface Factory {
        AddEventComponent create(@BindsInstance AddEventBottomDialog dialog);
    }

}
