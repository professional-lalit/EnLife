package com.enlife.app.screens.main.fragments.schedule;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.GoalEvent;

import java.util.Date;
import java.util.List;

public interface DailyScheduleContract {
    public interface ViewContract {
        void onEventsLoaded(List<GoalEvent> goalEvents);
        void onEventSaved(Event event);
    }

    public interface PresenterContract {
        void loadEvents(Date date);
        void saveEvent(Event event);
    }
}
