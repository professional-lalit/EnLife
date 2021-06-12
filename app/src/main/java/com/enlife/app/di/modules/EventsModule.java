package com.enlife.app.di.modules;

import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.di.scopes.FragmentScope;
import com.enlife.app.screens.main.dialog.EventsBottomDialog;
import com.enlife.app.screens.main.dialog.EventsBottomDialogPresenter;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventBottomDialog;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventContract;
import com.enlife.app.screens.main.fragments.goals.addevent.AddEventDialogPresenter;
import com.enlife.app.screens.main.fragments.home.HomeFragment;
import com.enlife.app.screens.main.fragments.home.HomeFragmentPresenter;
import com.enlife.app.utils.DateFormatter;

import dagger.Module;
import dagger.Provides;

@Module
public class EventsModule {

    @FragmentScope
    @Provides
    AddEventDialogPresenter addEventDialogPresenter(
            AddEventBottomDialog dialog,
            DateFormatter dateFormatter,
            EventDataOperator eventDataOperator
    ) {
        return new AddEventDialogPresenter(dateFormatter, eventDataOperator, dialog);
    }

    @FragmentScope
    @Provides
    EventsBottomDialogPresenter eventsBottomDialogPresenter(
            EventsBottomDialog dialog,
            DateFormatter dateFormatter,
            EventDataOperator eventDataOperator
    ) {
        return new EventsBottomDialogPresenter(dateFormatter, eventDataOperator, dialog);
    }

    @FragmentScope
    @Provides
    HomeFragmentPresenter homeFragmentPresenter(
            HomeFragment homeFragment,
            DateFormatter dateFormatter,
            EventDataOperator eventDataOperator
    ) {
        return new HomeFragmentPresenter(eventDataOperator, dateFormatter, homeFragment);
    }

}
