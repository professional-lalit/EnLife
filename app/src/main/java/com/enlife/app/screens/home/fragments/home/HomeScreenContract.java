package com.enlife.app.screens.home.fragments.home;

import com.enlife.app.database.models.DateEventCount;
import com.enlife.app.database.models.Event;
import com.enlife.app.models.CalendarDay;

import java.util.Calendar;
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
