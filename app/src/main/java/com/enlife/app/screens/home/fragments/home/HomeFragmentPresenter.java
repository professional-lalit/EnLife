package com.enlife.app.screens.home.fragments.home;

import androidx.core.util.Pair;

import com.enlife.app.models.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragmentPresenter {
    private final HomeFragmentContract homeFragmentContract;
    private String monthSelected = "";
    private Date cursorDay = new Date();

    public HomeFragmentPresenter(HomeFragmentContract homeFragmentContract) {
        this.homeFragmentContract = homeFragmentContract;
    }

    public void initializeCalendar() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("d", Locale.getDefault());
        SimpleDateFormat monthFormat = new SimpleDateFormat("M", Locale.getDefault());

        Calendar calendar = getModifiedCalendar(monthFormat, cursorDay);
        List<Pair<String, String>> days = getProcessedDays(dayFormat, monthFormat, calendar);
        List<CalendarDay> calendarDays = getCalendarDays(days);

        homeFragmentContract.setCalenderView(calendarDays);
    }

    private List<CalendarDay> getCalendarDays(List<Pair<String, String>> days) {
        List<CalendarDay> calendarDays = new ArrayList<>();
        for (Pair<String, String> day : days) {
            if (day != null) {
                assert day.first != null;
                calendarDays.add(
                        new CalendarDay(
                                Integer.parseInt(day.first),
                                false,
                                false,
                                monthSelected.equals(day.second))
                );
            }
        }
        return calendarDays;
    }

    private List<Pair<String, String>> getProcessedDays(
            SimpleDateFormat dayFormat,
            SimpleDateFormat monthFormat,
            Calendar calendar
    ) {
        List<Pair<String, String>> days = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Date date = new Date(calendar.getTimeInMillis());
            String day = dayFormat.format(date);
            String month = monthFormat.format(date);
            days.add(new Pair<>(day, month));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    private Calendar getModifiedCalendar(SimpleDateFormat monthFormat, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDay);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        monthSelected = monthFormat.format(date);
        return calendar;
    }

    public void onNext() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDay);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        cursorDay = calendar.getTime();
        initializeCalendar();
        homeFragmentContract.setMonthTitle(calendar);
    }

    public void onPrevious() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDay);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        cursorDay = calendar.getTime();
        initializeCalendar();
        homeFragmentContract.setMonthTitle(calendar);
    }

    public interface HomeFragmentContract {
        void setCalenderView(List<CalendarDay> calendarDays);

        void setMonthTitle(Calendar calendar);
    }
}
