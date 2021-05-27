package com.enlife.app.screens.main.fragments.goals.addgoal;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.Goal;
import com.enlife.app.database.models.GoalEvent;

import java.util.Date;
import java.util.List;

interface GoalManagementContract {

    interface ViewContract {
        void onDataSaved();
    }

    interface PresenterContract {
        void saveGoal(Goal goal);
    }
}
