package com.enlife.app.screens.main;

import com.enlife.app.di.modules.MainModule;
import com.enlife.app.di.scopes.ActivityScope;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);

    @Subcomponent.Factory
    interface Factory {
        MainComponent create(@BindsInstance MainActivity mainActivity);
    }

}
