package com.enlife.app.screens.main.fragments.goals.addmilestone;

import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.utils.DateFormatter;

public class AddMilestoneDialogPresenter implements AddMilestoneContract.ViewContract{

    private final DateFormatter dateFormatter;
    private final DatabaseOperator databaseOperator;

    public AddMilestoneDialogPresenter(DateFormatter dateFormatter, DatabaseOperator databaseOperator) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
    }

    @Override
    public void onMilestoneAdded(Milestone milestone) {

    }
}
