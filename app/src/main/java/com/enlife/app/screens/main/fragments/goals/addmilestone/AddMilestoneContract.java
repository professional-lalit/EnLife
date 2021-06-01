package com.enlife.app.screens.main.fragments.goals.addmilestone;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.Milestone;

import java.util.Date;
import java.util.List;

public interface AddMilestoneContract {
    interface ViewContract {
        void onMilestoneAdded(Milestone milestone);
    }

    interface PresenterContract {
        void addMilestone(String title, String description, Date fromDate, Date toDate, List<Event> eventsAdded);
    }
}
