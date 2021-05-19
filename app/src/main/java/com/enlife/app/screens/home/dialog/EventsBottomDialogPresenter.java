package com.enlife.app.screens.home.dialog;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.di.HomeComponent;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;

import javax.inject.Inject;

public class EventsBottomDialogPresenter implements EventsBottomContract.PresenterContract {

    private final EventsBottomContract.ViewContract viewContract;

    @Inject
    DateFormatter dateFormatter;

    @Inject
    EventDataOperator databaseOperator;

    public EventsBottomDialogPresenter(EventsBottomContract.ViewContract viewContract) {
        this.viewContract = viewContract;
        HomeComponent homeComponent = CustomApplication
                .getInstance()
                .applicationComponent
                .homeComponent()
                .create();
        homeComponent.inject(this);
    }

    @Override
    public void loadEvents(Date date) {
        String formattedDate = dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, date);
        viewContract.onEventsLoaded(databaseOperator.getList(formattedDate));
    }
}
