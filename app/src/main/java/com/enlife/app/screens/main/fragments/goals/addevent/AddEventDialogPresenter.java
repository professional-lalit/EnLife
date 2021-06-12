package com.enlife.app.screens.main.fragments.goals.addevent;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;

public class AddEventDialogPresenter implements AddEventContract.PresenterContract {

    private final DateFormatter dateFormatter;
    private final EventDataOperator databaseOperator;

    private AddEventContract.ViewContract viewContract;

    public AddEventDialogPresenter(DateFormatter dateFormatter,
                                   EventDataOperator databaseOperator,
                                   AddEventContract.ViewContract viewContract) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
        this.viewContract = viewContract;
    }

    @Override
    public void addEvent(String title, String description, Date eventDate, Date fromTime, Date toTime) {
        Event event = new Event(0L,
                title,
                description,
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, eventDate),
                false,
                "",
                Event.RepeatMode.NONE,
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.HH_mm_a, fromTime),
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.HH_mm_a, toTime),
                "",
                0L,
                0L
        );
        viewContract.onEventAdded(event);
    }
}
