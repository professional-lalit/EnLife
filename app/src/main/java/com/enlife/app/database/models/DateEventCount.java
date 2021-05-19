package com.enlife.app.database.models;

public class DateEventCount {
    private final String date;
    private final int count;

    public DateEventCount(String date, int count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }
}
