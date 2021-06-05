package com.enlife.app.di.components;

import com.enlife.app.di.modules.MainModule;
import com.enlife.app.di.scopes.PerScreen;
import com.enlife.app.screens.main.HomeActivity;

import dagger.Subcomponent;

@PerScreen
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {

    void inject(HomeActivity homeActivity);

    @Subcomponent.Builder
    interface Builder {
        MainComponent build();
    }

}
