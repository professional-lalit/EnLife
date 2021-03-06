package com.enlife.app.screens.main.dialog;

import com.enlife.app.di.modules.EventsModule;
import com.enlife.app.di.scopes.FragmentScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {EventsModule.class})
public interface EventsBottomDialogComponent {

    void inject(EventsBottomDialog dialog);

    @Subcomponent.Factory
    interface Factory {
        EventsBottomDialogComponent create(@BindsInstance EventsBottomDialog dialog);
    }

}
