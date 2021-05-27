package com.enlife.app.database.models;

import java.util.List;

public class Milestone {
    private final long milestoneId;
    private final String title;
    private final String description;
    private final String fromDate;
    private final String toDate;
    private final List<Event> events;

    public Milestone(long milestoneId, String title, String description,
                     String fromDate, String toDate, List<Event> events) {
        this.milestoneId = milestoneId;
        this.title = title;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.events = events;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public List<Event> getEvents() {
        return events;
    }
}
