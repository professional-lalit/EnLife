package com.enlife.app.database.models;

import androidx.annotation.Nullable;

import java.util.List;

public class Goal {
    private final long goalId;
    private final String title;
    private final String description;
    private final String goalType;
    private final String fromDate;
    private final String toDate;

    @Nullable
    private final List<Milestone> milestones;

    public Goal(
            long goalId, String title, String description,
            String goalType, String fromDate, String toDate,
            @Nullable List<Milestone> milestones
    ) {
        this.goalId = goalId;
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
