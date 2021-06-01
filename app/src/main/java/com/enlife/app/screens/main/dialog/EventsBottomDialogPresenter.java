package com.enlife.app.screens.main.dialog;

import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;

public class EventsBottomDialogPresenter implements EventsBottomContract.PresenterContract {

    private EventsBottomContract.ViewContract viewContract;

    DateFormatter dateFormatter;
    EventDataOperator databaseOperator;

    public EventsBottomDialogPresenter(DateFormatter dateFormatter, EventDataOperator databaseOperator) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
    }

    @Override
    public void loadEvents(Date date) {
        String formattedDate = dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, date);
        viewContract.onEventsLoaded(databaseOperator.getList(formattedDate));
    }

    public void setViewContract(EventsBottomContract.ViewContract viewContract) {
        this.viewContract = viewContract;
    }
}
