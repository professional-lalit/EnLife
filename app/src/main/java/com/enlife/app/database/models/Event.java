package com.enlife.app.database.models;

import androidx.annotation.Nullable;

public class Event {
    @Nullable
    private final Long id;
    private String title;
    private String description;
    private String date;
    private boolean isAllDay;
    @Nullable
    private String location;
    private RepeatMode repeatMode;
    private String fromTime;
    private String toTime;
    @Nullable
    private String imagePath;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public void setLocation(@Nullable String location) {
        this.location = location;
    }

    public void setRepeatMode(RepeatMode repeatMode) {
        this.repeatMode = repeatMode;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public void setImagePath(@Nullable String imagePath) {
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