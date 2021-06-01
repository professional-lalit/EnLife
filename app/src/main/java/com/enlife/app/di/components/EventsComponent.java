package com.enlife.app.di.components;

import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.di.modules.EventsModule;
import com.enlife.app.di.scopes.PerScreen;
import com.enlife.app.screens.main.dialog.EventsBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventDialogPresenter;
import com.enlife.app.screens.main.fragments.home.HomeFragment;
import com.enlife.app.utils.DateFormatter;

import dagger.Provides;
import dagger.Subcomponent;

@PerScreen
@Subcomponent(modules = {EventsModule.class})
public interface EventsComponent {

    void inject(AddEventBottomDialog dialog);

    void inject(EventsBottomDialog dialog);

    void inject(HomeFragment dialog);


    @Subcomponent.Builder
    interface Builder {
        EventsComponent build();
    }

}
