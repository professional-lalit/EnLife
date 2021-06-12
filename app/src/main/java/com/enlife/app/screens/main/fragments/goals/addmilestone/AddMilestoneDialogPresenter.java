package com.enlife.app.screens.main.fragments.goals.addmilestone;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;
import java.util.List;

public class AddMilestoneDialogPresenter implements AddMilestoneContract.PresenterContract {

    private final DateFormatter dateFormatter;
    private final DatabaseOperator databaseOperator;
    private AddMilestoneContract.ViewContract viewContract;

    public AddMilestoneDialogPresenter(
            DateFormatter dateFormatter,
            DatabaseOperator databaseOperator,
            AddMilestoneContract.ViewContract viewContract
    ) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
        this.viewContract = viewContract;
    }

    @Override
    public void addMilestone(String title, String description, Date fromDate, Date toDate, List<Event> eventsAdded) {
        Milestone milestone = new Milestone(
                0L,
                title,
                description,
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, fromDate),
                dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, toDate),
                eventsAdded,
                0L
        );
        viewContract.onMilestoneAdded(milestone);
    }
}
