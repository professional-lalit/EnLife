package com.enlife.app.screens.home.fragments.home;

import androidx.core.util.Pair;

import com.enlife.app.database.operators.DatabaseOperator;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.models.CalendarDay;
import com.enlife.app.utils.DateFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragmentPresenter implements HomeScreenContract.PresenterContract {

    private final HomeScreenContract.ViewContract viewContract;
    private String monthSelected = "";
    private Date cursorDate = new Date();
    private final DateFormatter dateFormatter = new DateFormatter();
    private final DatabaseOperator databaseOperator = new EventDataOperator();

    public HomeFragmentPresenter(HomeScreenContract.ViewContract viewContract) {
        this.viewContract = viewContract;
    }

    private void updateCalendar() {
        Calendar calendar = getModifiedCalendar(cursorDate);
        List<Pair<String, String>> days = getProcessedDays(calendar);
        List<CalendarDay> calendarDays = getCalendarDays(days);
        viewContract.setCalenderView(calendarDays);
    }

    private List<CalendarDay> getCalendarDays(List<Pair<String, String>> days) {
        String cursorDay = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_DAY, cursorDate);
        String cursorMonth = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_MONTH, cursorDate);

        List<CalendarDay> calendarDays = new ArrayList<>();
        for (Pair<String, String> day : days) {
            if (day != null) {
                assert day.first != null;
                calendarDays.add(
                        new CalendarDay(
                                Integer.parseInt(day.first),
                                false,
                                cursorDay.equals(day.first) && cursorMonth.equals(day.second),
                                monthSelected.equals(day.second))
                );
            }
        }
        return calendarDays;
    }

    private List<Pair<String, String>> getProcessedDays(Calendar calendar) {
        List<Pair<String, String>> days = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            Date date = new Date(calendar.getTimeInMillis());
            String day = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_DAY, date);
            String month = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_MONTH, date);
            days.add(new Pair<>(day, month));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    private Calendar getModifiedCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDate);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        monthSelected = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_MONTH, date);
        return calendar;
    }

    public void setCursorDate(Date cursorDate) {
        this.cursorDate = cursorDate;
    }

    public Date getCursorDate() {
        return cursorDate;
    }

    @Override
    public void onViewInitialized() {
        updateCalendar();
    }

    @Override
    public void onNext() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        cursorDate = calendar.getTime();
        updateCalendar();
        viewContract.setMonthTitle(dateFormatter.getFormattedDate(DateFormatter.DateFormat.FULL_NAME_MONTH, calendar.getTime()));
        fetchEventsForDay();
    }

    @Override
    public void onPrevious() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        cursorDate = calendar.getTime();
        updateCalendar();
        viewContract.setMonthTitle(dateFormatter.getFormattedDate(DateFormatter.DateFormat.FULL_NAME_MONTH, calendar.getTime()));
        fetchEventsForDay();
    }

    @Override
    public void onDaySelected(List<CalendarDay> calendarDays, CalendarDay selectedCalendarDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDate);
        calendar.set(Calendar.DAY_OF_MONTH, selectedCalendarDay.getDate());
        cursorDate = calendar.getTime();

        for (int i = 0; i < calendarDays.size(); i++) {
            if (calendarDays.get(i).isSelected()) {
                calendarDays.get(i).setSelected(false);
                viewContract.onDayUpdated(i);
                break;
            }
        }
        int selectedPosition = calendarDays.indexOf(selectedCalendarDay);
        selectedCalendarDay.setSelected(true);
        viewContract.onDayUpdated(selectedPosition);
    }

    @Override
    public void fetchEventsForDay() {
        String date = dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, cursorDate);
        viewContract.onEventsFetched(databaseOperator.getList(date));
    }
}
