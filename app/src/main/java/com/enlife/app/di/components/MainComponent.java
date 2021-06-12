package com.enlife.app.di.components;

import com.enlife.app.di.modules.MainModule;
import com.enlife.app.di.scopes.ActivityScope;
import com.enlife.app.screens.main.MainActivity;

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
