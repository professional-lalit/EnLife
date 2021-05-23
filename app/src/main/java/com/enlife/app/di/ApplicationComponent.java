package com.enlife.app.di;

import com.enlife.app.screens.main.fragments.home.HomeFragment;

import dagger.Component;

@Component(modules = {DatabaseModule.class, UtilityModule.class, SubComponentsModule.class})
public interface ApplicationComponent {

    void inject(HomeFragment homeFragment);

    HomeComponent.Factory homeComponent();

}
