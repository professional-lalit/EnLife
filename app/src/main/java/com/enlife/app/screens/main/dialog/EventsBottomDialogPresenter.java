package com.enlife.app.screens.main.dialog;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;

public class EventsBottomDialogPresenter implements EventsBottomContract.PresenterContract {

    private final EventsBottomContract.ViewContract viewContract;

    DateFormatter dateFormatter;
    EventDataOperator databaseOperator;

    public EventsBottomDialogPresenter(
            DateFormatter dateFormatter,
            EventDataOperator databaseOperator,
            EventsBottomContract.ViewContract viewContract
    ) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
        this.viewContract = viewContract;
    }

    @Override
    public void loadEvents(Date date) {
        String formattedDate = dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, date);
        viewContract.onEventsLoaded(databaseOperator.getList(formattedDate));
    }

    @Override
    public void saveEvent(Event event) {
        databaseOperator.addData(event);
        viewContract.onEventSaved(event);
    }

}
