package com.enlife.app.screens.main.fragments.goals.addmilestone;

import com.enlife.app.database.models.Milestone;

public interface AddMilestoneContract {
    interface ViewContract{
        void onMilestoneAdded(Milestone milestone);
    }

    interface PresenterContract{
        void addMilestone(Milestone milestone);
    }
}
