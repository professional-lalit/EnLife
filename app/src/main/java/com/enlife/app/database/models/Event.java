package com.enlife.app.database.models;

import androidx.annotation.Nullable;

public class Event {
    @Nullable
    private final Long id;
    private final String title;
    private final String description;
    private final String date;
    private final boolean isAllDay;
    @Nullable
    private final String location;
    @Nullable
    private final RepeatMode repeatMode;
    private final String fromTime;
    private final String toTime;
    @Nullable
    private final String imagePath;

    public Event(Long id, String title, String description, String date, boolean isAllDay, String location,
                 RepeatMode repeatMode, String fromTime, String toTime, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.isAllDay = isAllDay;
        this.location = location;
        this.repeatMode = repeatMode;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public String getLocation() {
        return location;
    }

    public RepeatMode getRepeatMode() {
        return repeatMode;
    }

    public String getFromTime() {
        return fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public enum RepeatMode {

        DAILY("daily"), WEEKLY("weekly"), MONTHLY("monthly"), NONE("none");

        private final String mode;

        RepeatMode(String mode) {
            this.mode = mode;
        }

        public String getMode() {
            return mode;
        }
    }
}
