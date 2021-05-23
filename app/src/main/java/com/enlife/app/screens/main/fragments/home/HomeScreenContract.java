package com.enlife.app.screens.main.fragments.home;

import com.enlife.app.models.CalendarDay;

import java.util.List;

public interface HomeScreenContract {

    interface ViewContract {
        void setCalenderView(List<CalendarDay> calendarDays);

        void setMonthTitle(String monthTitle);

        void onDayUpdated(int position);
    }

    interface PresenterContract {
        void onViewInitialized();

        void onNext();

        void onPrevious();

        void onDaySelected(List<CalendarDay> calendarDays, CalendarDay selectedCalendarDay);
    }
}
