package com.enlife.app.models;

public class CalendarDay {
    private final int date;
    private final boolean hasContent;
    private boolean isSelected;
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

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isCurrentMonthDay() {
        return isCurrentMonthDay;
    }
}
