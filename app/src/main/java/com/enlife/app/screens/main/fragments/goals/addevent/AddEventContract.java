package com.enlife.app.screens.main.fragments.goals.addevent;

import com.enlife.app.database.models.Event;

public interface AddEventContract {
    interface ViewContract{
        void onEventAdded(Event event);
    }
    interface PresenterContract{
        void addEvent();
    }
}
