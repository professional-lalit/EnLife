package com.enlife.app.screens.home.dialog;

import com.enlife.app.database.models.Event;

import java.util.Date;
import java.util.List;

public interface EventsBottomContract {
    interface PresenterContract {
        void loadEvents(Date date);
    }

    interface ViewContract {
        void onEventsLoaded(List<Event> events);
    }
}
