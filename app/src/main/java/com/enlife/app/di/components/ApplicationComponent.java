package com.enlife.app.di.components;

import com.enlife.app.di.modules.DatabaseModule;
import com.enlife.app.di.modules.GoalManagementModule;
import com.enlife.app.di.modules.UtilityModule;
import com.enlife.app.screens.main.fragments.home.HomeFragment;

import dagger.Component;

@Component(modules = {
        DatabaseModule.class,
        UtilityModule.class,
        GoalManagementModule.class
})
public interface ApplicationComponent {

    void inject(Object object);
}
