package com.enlife.app.screens.home.fragments.home;

import com.enlife.app.models.CalendarDay;

import java.util.Calendar;
import java.util.List;

public interface HomeScreenContract {

    interface ViewContract {
        void setCalenderView(List<CalendarDay> calendarDays);

        void setMonthTitle(Calendar calendar);
    }

    interface PresenterContract {
        public void onViewInitialized();

        public void onNext();

        public void onPrevious();
    }
}
