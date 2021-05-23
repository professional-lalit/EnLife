package com.enlife.app.screens.main.fragments.home.calendar;

import com.enlife.app.models.CalendarDay;

public interface DayClickListener {
    void onDayClicked(int position, CalendarDay calendarDay);
}
