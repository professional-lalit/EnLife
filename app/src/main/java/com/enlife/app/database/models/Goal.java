package com.enlife.app.database.models;

import java.util.List;

public class Goal {
    private final String title;
    private final String description;
    private final String goalType;
    private final String fromDate;
    private final String toDate;
    private final List<Milestone> milestones;

    public Goal(
            String title, String description,
            String goalType, String fromDate, String toDate,
            List<Milestone> milestones
    ) {
        this.title = title;
        this.description = description;
        this.goalType = goalType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.milestones = milestones;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGoalType() {
        return goalType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }
}
