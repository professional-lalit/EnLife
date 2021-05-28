package com.enlife.app.screens.main.fragments.goals.addgoal;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.Goal;
import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.utils.DateFormatter;

public class GoalManagementPresenter implements GoalManagementContract.PresenterContract {

    private final DateFormatter dateFormatter;
    private final DatabaseOperator<Goal> goalDatabaseOperator;
    private final DatabaseOperator<Milestone> milestoneDatabaseOperator;
    private final DatabaseOperator<Event> eventDatabaseOperator;
    private final GoalManagementContract.ViewContract viewContract;

    public GoalManagementPresenter(GoalManagementContract.ViewContract viewContract,
                                   DateFormatter dateFormatter,
                                   DatabaseOperator<Goal> goalDatabaseOperator,
                                   DatabaseOperator<Milestone> milestoneDatabaseOperator,
                                   DatabaseOperator<Event> eventDatabaseOperator
    ) {
        this.viewContract = viewContract;
        this.dateFormatter = dateFormatter;
        this.goalDatabaseOperator = goalDatabaseOperator;
        this.milestoneDatabaseOperator = milestoneDatabaseOperator;
        this.eventDatabaseOperator = eventDatabaseOperator;
    }

    @Override
    public void saveGoal(Goal goal) {
        if (!goal.getMilestones().isEmpty()) {
            for (Milestone milestone : goal.getMilestones()) {
                if (!milestone.getEvents().isEmpty()) {
                    eventDatabaseOperator.addList(milestone.getEvents());
                }
                milestoneDatabaseOperator.addData(milestone);
            }
        }
        goalDatabaseOperator.addData(goal);
        viewContract.onDataSaved();
    }
}
