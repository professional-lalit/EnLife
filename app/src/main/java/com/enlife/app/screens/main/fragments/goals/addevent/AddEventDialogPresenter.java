package com.enlife.app.screens.main.fragments.goals.addevent;

import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.utils.DateFormatter;

public class AddEventDialogPresenter implements AddEventContract.PresenterContract{

    private final DateFormatter dateFormatter;
    private final DatabaseOperator databaseOperator;

    public AddEventDialogPresenter(DateFormatter dateFormatter, DatabaseOperator databaseOperator) {
        this.dateFormatter = dateFormatter;
        this.databaseOperator = databaseOperator;
    }

    @Override
    public void addEvent() {

    }
}
