package com.enlife.app.screens.main.fragments.goals.addevent;

import com.enlife.app.database.models.GoalEvent;

public interface AddEventContract {
    interface ViewContract{
        void onEventAdded(GoalEvent event);
    }
    interface PresenterContract{
        void addEvent();
    }
}
