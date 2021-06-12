package com.enlife.app.screens.main.fragments.home;

import com.enlife.app.di.modules.EventsModule;
import com.enlife.app.di.scopes.FragmentScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = {EventsModule.class})
public interface HomeFragmentComponent {

    void inject(HomeFragment dialog);

    @Subcomponent.Factory
    interface Factory {
        HomeFragmentComponent create(@BindsInstance HomeFragment fragment);
    }
}
