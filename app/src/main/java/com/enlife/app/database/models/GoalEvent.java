package com.enlife.app.database.models;

public class GoalEvent {
    private String goalId;
    private String milestoneId;
    private String title;
    private String description;
    private String fromTime;
    private String toTime;

    public GoalEvent(
            String goalId, String milestoneId,
            String title, String description,
            String fromTime, String toTime
    ) {
        this.goalId = goalId;
        this.milestoneId = milestoneId;
        this.title = title;
        this.description = description;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public String getGoalId() {
        return goalId;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getToTime() {
        return toTime;
    }
}
