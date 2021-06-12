package com.enlife.app.screens.main.fragments.goals.addgoal;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.Goal;
import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Calendar;
import java.util.Date;

public class AddGoalFragmentPresenter implements GoalManagementContract.PresenterContract {

    private final DateFormatter dateFormatter;
    private final DatabaseOperator<Goal> goalDatabaseOperator;
    private final DatabaseOperator<Milestone> milestoneDatabaseOperator;
    private final DatabaseOperator<Event> eventDatabaseOperator;
    private GoalManagementContract.ViewContract viewContract;

    public AddGoalFragmentPresenter(
            DateFormatter dateFormatter,
            DatabaseOperator<Goal> goalDatabaseOperator,
            DatabaseOperator<Milestone> milestoneDatabaseOperator,
            DatabaseOperator<Event> eventDatabaseOperator,
            GoalManagementContract.ViewContract viewContract
    ) {
        this.dateFormatter = dateFormatter;
        this.goalDatabaseOperator = goalDatabaseOperator;
        this.milestoneDatabaseOperator = milestoneDatabaseOperator;
        this.eventDatabaseOperator = eventDatabaseOperator;
        this.viewContract = viewContract;
    }

    @Override
    public void saveGoal(Goal goal) {
        Calendar calendar = Calendar.getInstance();
        long goalId = goalDatabaseOperator.addData(goal);
        if (goal.getMilestones() != null && !goal.getMilestones().isEmpty()) {
            for (Milestone milestone : goal.getMilestones()) {
                milestone.setGoalId(goalId);
                long milestoneId = milestoneDatabaseOperator.addData(milestone);
                if (!milestone.getEvents().isEmpty()) {
                    for (Event event : milestone.getEvents()) {
                        //Each event for each date in milestone's date range
                        createEventForEachDayInMilestoneRange(calendar, goalId, milestone, milestoneId, event);
                    }
                }
            }
        }
        viewContract.onDataSaved();
    }

    private void createEventForEachDayInMilestoneRange(Calendar calendar, long goalId,
                                                       Milestone milestone, long milestoneId, Event event) {
        Date fromDate = dateFormatter.getDate(
                DateFormatter.DateFormat.INDIAN_DATE_FORMAT,
                milestone.getFromDate()
        );

        Date toDate = dateFormatter.getDate(
                DateFormatter.DateFormat.INDIAN_DATE_FORMAT,
                milestone.getToDate()
        );

        if (fromDate != null && toDate != null) {
            calendar.setTime(fromDate);
            while (!calendar.getTime().after(toDate)) {
                Event perDayEvent;
                try {
                    perDayEvent = event.clone();
                    perDayEvent.setDate(dateFormatter.getFormattedDate(
                            DateFormatter.DateFormat.INDIAN_DATE_FORMAT, calendar.getTime())
                    );
                    perDayEvent.setMilestoneId(milestoneId);
                    perDayEvent.setGoalId(goalId);
                    eventDatabaseOperator.addData(perDayEvent);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
