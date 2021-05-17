package com.enlife.app.screens.home.dialog;

import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;

public class EventsBottomDialogPresenter implements EventsBottomContract.PresenterContract {

    private final EventsBottomContract.ViewContract viewContract;
    private final DateFormatter dateFormatter = new DateFormatter();
    private final DatabaseOperator databaseOperator = new EventDataOperator();

    public EventsBottomDialogPresenter(EventsBottomContract.ViewContract viewContract) {
        this.viewContract = viewContract;
    }

    @Override
    public void loadEvents(Date date) {
        String formattedDate = dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, date);
        viewContract.onEventsLoaded(databaseOperator.getList(formattedDate));
    }
}
