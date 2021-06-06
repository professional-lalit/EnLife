package com.enlife.app.screens.main.fragments.schedule;

import com.enlife.app.database.models.GoalEvent;

import java.util.Date;
import java.util.List;

public interface DailyScheduleContract {
    public interface ViewContract {
        void onEventsLoaded(List<GoalEvent> goalEvents);
    }

    public interface PresenterContract {
        void loadEvents(Date date);
    }
}
