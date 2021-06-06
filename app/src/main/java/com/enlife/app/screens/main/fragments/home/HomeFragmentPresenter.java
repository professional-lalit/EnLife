package com.enlife.app.screens.main.fragments.home;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.DateEventCount;
import com.enlife.app.database.operators.EventDataOperator;
import com.enlife.app.models.CalendarDay;
import com.enlife.app.utils.DateFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class HomeFragmentPresenter implements HomeScreenContract.PresenterContract {

    private Date cursorDate = new Date();
    EventDataOperator databaseOperator;
    DateFormatter dateFormatter;

    private HomeScreenContract.ViewContract viewContract;


    public HomeFragmentPresenter(EventDataOperator databaseOperator, DateFormatter dateFormatter) {
        this.databaseOperator = databaseOperator;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public void onViewInitialized() {
        updateCalendar();
    }

    private void updateCalendar() {
        Calendar calendar = getModifiedCalendar();
        List<CalendarDay> calendarDays = getProcessedDays(calendar);
        viewContract.setCalenderView(calendarDays);
    }

    private List<CalendarDay> getProcessedDays(Calendar calendar) {
        List<CalendarDay> calendarDays = new ArrayList<>();

        String currentMonth = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_MONTH, cursorDate);
        String currentDay = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_DAY, cursorDate);
        List<DateEventCount> dateEventCountList = databaseOperator.getDateEventCounts();

        for (int i = 0; i < 35; i++) {
            Date date = new Date(calendar.getTimeInMillis());
            String day = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_DAY, date);
            String month = dateFormatter.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_MONTH, date);
            String dayForComparison = dateFormatter.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, date);

            boolean hasContent = false;
            for (DateEventCount dateEventCount : dateEventCountList) {
                if (dateEventCount.getDate().equals(dayForComparison) && dateEventCount.getCount() > 0) {
                    hasContent = true;
                    break;
                }
            }

            calendarDays.add(
                    new CalendarDay(
                            Integer.parseInt(day),
                            hasContent,
                            day.equals(currentDay),
                            month.equals(currentMonth)
                    )
            );

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return calendarDays;
    }

    private Calendar getModifiedCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDate);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar;
    }

    public void setCursorDate(Date cursorDate) {
        this.cursorDate = cursorDate;
    }

    public Date getCursorDate() {
        return cursorDate;
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
    public void reloadEvents() {
        updateCalendar();
    }

    public void setViewContract(HomeScreenContract.ViewContract viewContract) {
        this.viewContract = viewContract;
    }
}
