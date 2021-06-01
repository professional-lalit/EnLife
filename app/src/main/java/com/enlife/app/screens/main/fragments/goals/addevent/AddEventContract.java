package com.enlife.app.screens.main.fragments.goals.addevent;

import com.enlife.app.database.models.Event;

import java.util.Date;

public interface AddEventContract {
    interface ViewContract {
        void onEventAdded(Event event);
    }

    interface PresenterContract {
        void addEvent(String title, String description, Date eventDate, Date fromTime, Date toTime);
    }
}
