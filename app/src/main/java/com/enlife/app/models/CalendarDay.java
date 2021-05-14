package com.enlife.app.models;

public class CalendarDay {
    private final int date;
    private final boolean hasContent;
    private final boolean isSelected;
    private final boolean isCurrentMonthDay;

    public CalendarDay(int date, boolean hasContent, boolean isSelected, boolean isCurrentMonthDay) {
        this.date = date;
        this.hasContent = hasContent;
        this.isSelected = isSelected;
        this.isCurrentMonthDay = isCurrentMonthDay;
    }

    public int getDate() {
        return date;
    }

    public boolean isHasContent() {
        return hasContent;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isCurrentMonthDay() {
        return isCurrentMonthDay;
    }
}
