package com.enlife.app.screens.main.dialog;

import com.enlife.app.database.models.Event;

import java.util.Date;
import java.util.List;

public interface EventsBottomContract {
    interface PresenterContract {
        void loadEvents(Date date);

        void saveEvent(Event event);
    }

    interface ViewContract {
        void onEventsLoaded(List<Event> events);
        void onEventSaved(Event event);
    }
}
