package com.enlife.app.di.modules;

import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.di.scopes.PerScreen;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventDialogPresenter;
import com.enlife.app.utils.DateFormatter;

import dagger.Module;
import dagger.Provides;

@Module
public class EventsModule {

    @PerScreen
    @Provides
    AddEventDialogPresenter provideAddEventDialogPresenter(DateFormatter dateFormatter, EventDataOperator eventDataOperator) {
        return new AddEventDialogPresenter(dateFormatter, eventDataOperator);
    }

}
