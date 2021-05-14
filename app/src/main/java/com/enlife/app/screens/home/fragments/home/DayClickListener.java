package com.enlife.app.screens.home.fragments.home;

import com.enlife.app.models.CalendarDay;

public interface DayClickListener {
    void onDayClicked(int position, CalendarDay calendarDay);
}
