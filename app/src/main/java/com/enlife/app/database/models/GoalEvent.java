package com.enlife.app.database.models;

public class GoalEvent {
    private final Event event;
    private final Goal goal;
    private final Milestone milestone;

    public GoalEvent(Event event, Goal goal, Milestone milestone) {
        this.event = event;
        this.goal = goal;
        this.milestone = milestone;
    }

    public Event getEvent() {
        return event;
    }

    public Goal getGoal() {
        return goal;
    }

    public Milestone getMilestone() {
        return milestone;
    }
}
