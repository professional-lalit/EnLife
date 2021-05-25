package com.enlife.app.screens.main.fragments.goals.addgoal;

import com.enlife.app.database.models.Goal;
import com.enlife.app.database.models.GoalEvent;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.utils.DateFormatter;

import java.util.Collections;
import java.util.List;

public class GoalManagementPresenter implements GoalManagementContract.PresenterContract {

    private final DateFormatter dateFormatter;
    private final DatabaseOperator databaseOperator;

    public GoalManagementPresenter(DateFormatter dateFormatter, DatabaseOperator databaseOperator) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
    }

    @Override
    public void addEvents(List<GoalEvent> goalEvents) {
        databaseOperator.addList(Collections.singletonList(goalEvents));
    }

    @Override
    public void addGoal(Goal goal) {
        databaseOperator.addData(goal);
    }
}
