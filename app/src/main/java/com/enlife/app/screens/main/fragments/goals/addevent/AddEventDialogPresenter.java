package com.enlife.app.screens.main.fragments.goals.addevent;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;

public class AddEventDialogPresenter implements AddEventContract.PresenterContract {

    private final DateFormatter dateFormatter;
    private final DatabaseOperator databaseOperator;

    private AddEventContract.ViewContract viewContract;

    public AddEventDialogPresenter(DateFormatter dateFormatter, DatabaseOperator databaseOperator) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
    }

    public void setViewContract(AddEventContract.ViewContract viewContract) {
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
