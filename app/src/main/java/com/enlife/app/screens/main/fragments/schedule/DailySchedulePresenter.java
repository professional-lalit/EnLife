package com.enlife.app.screens.main.fragments.schedule;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.GoalEvent;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.database.operators.GoalEventDataOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Date;
import java.util.List;

public class DailySchedulePresenter implements DailyScheduleContract.PresenterContract {

    private DailyScheduleContract.ViewContract viewContract;
    private final GoalEventDataOperator goalEventDataOperator;
    private final EventDataOperator eventDataOperator;
    private final DateFormatter dateFormatter;

    public DailySchedulePresenter(GoalEventDataOperator goalEventDataOperator,
                                  EventDataOperator eventDataOperator,
                                  DateFormatter dateFormatter) {
        this.goalEventDataOperator = goalEventDataOperator;
        this.eventDataOperator = eventDataOperator;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public void loadEvents(Date date) {
        List<GoalEvent> goalEvents = goalEventDataOperator.getList(dateFormatter.getFormattedDate(
                DateFormatter.DateFormat.INDIAN_DATE_FORMAT, date)
        );
        viewContract.onEventsLoaded(goalEvents);
    }

    @Override
    public void saveEvent(Event event) {
        eventDataOperator.addData(event);
        viewContract.onEventSaved(event);
    }

    public void setViewContract(DailyScheduleContract.ViewContract viewContract) {
        this.viewContract = viewContract;
    }
}
