package com.enlife.app.presenters_tests;

import com.enlife.app.models.CalendarDay;
import com.enlife.app.screens.home.fragments.home.HomeFragmentPresenter;
import com.enlife.app.screens.home.fragments.home.HomeScreenContract;
import com.enlife.app.utils.DateFormatter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragmentPresenterTest {

    private final StubViewContract viewContract = new StubViewContract();
    private final HomeFragmentPresenter SUT = new HomeFragmentPresenter(viewContract);

    private Date getPredefinedCursorDate(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }

    private final DateFormatter dateFormatter = new DateFormatter();

    private static class StubViewContract implements HomeScreenContract.ViewContract {

        private List<CalendarDay> calendarDays;
        private String monthTitle;

        public List<CalendarDay> getCalendarDays() {
            return calendarDays;
        }

        @Override
        public void setCalenderView(List<CalendarDay> calendarDays) {
            this.calendarDays = calendarDays;
        }

        @Override
        public void setMonthTitle(String monthTitle) {
            this.monthTitle = monthTitle;
        }

        @Override
        public void onDayUpdated(int position) {
        }

        public String getMonthTitle() {
            return monthTitle;
        }
    }

    @Test
    public void next_month_selected_month_title_check() {
        //Arrange
        Date cursorDate = SUT.getCursorDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDate);
        calendar.add(Calendar.MONTH, 1);
        String actualMonthTitle = dateFormatter.getFormattedDate(DateFormatter.DateFormat.FULL_NAME_MONTH, calendar.getTime());
        //Act
        SUT.onNext();
        //Assert
        String expectedMonthTitle = viewContract.getMonthTitle();
        Assert.assertEquals(expectedMonthTitle, actualMonthTitle);
    }

    @Test
    public void previous_month_selected_month_title_check() {
        //Arrange
        Date cursorDate = SUT.getCursorDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cursorDate);
        calendar.add(Calendar.MONTH, -1);
        String actualMonthTitle = dateFormatter.getFormattedDate(DateFormatter.DateFormat.FULL_NAME_MONTH, calendar.getTime());

        //Act
        SUT.onPrevious();

        //Assert
        String expectedMonthTitle = viewContract.getMonthTitle();
        Assert.assertEquals(expectedMonthTitle, actualMonthTitle);
    }

    /**
     * The days in calendar should be verified looking into grid structure of
     * a typical 7 x 7 calendar, by default the current day is selected.
     * <p>
     * The default date for testing is 01/01/2021
     * Hence, selected day -> 14th of January 2021
     * starting day of grid -> 27th of December 2020
     * ending day of grid -> 2nd of February 2021
     */
    @Test
    public void calendar_month_days_check() {
        //Arrange
        Date cursorDate = getPredefinedCursorDate(Calendar.JANUARY, 2021);
        SUT.setCursorDate(cursorDate);

        //Act
        SUT.onViewInitialized();

        //Assert
        verifyMonthDays();
    }

    @Test
    public void calendar_next_month_days_check() {
        //Arrange
        Date cursorDate = getPredefinedCursorDate(Calendar.DECEMBER, 2020);
        SUT.setCursorDate(cursorDate);

        //Act
        SUT.onNext();

        //Assert
        verifyMonthDays();
    }

    @Test
    public void calendar_previous_month_days_check() {
        //Arrange
        Date cursorDate = getPredefinedCursorDate(Calendar.FEBRUARY, 2021);
        SUT.setCursorDate(cursorDate);

        //Act
        SUT.onPrevious();

        //Assert
        verifyMonthDays();
    }

    private void verifyMonthDays() {
        List<CalendarDay> calendarDays = viewContract.getCalendarDays();
        //verifying boundary conditions of month
        Assert.assertEquals(27, calendarDays.get(0).getDate());
        Assert.assertEquals(30, calendarDays.get(calendarDays.size() - 1).getDate());
        //verifying the start of month
        Assert.assertFalse(calendarDays.get(0).isCurrentMonthDay());
        Assert.assertFalse(calendarDays.get(0).isSelected());
        Assert.assertTrue(calendarDays.get(5).isCurrentMonthDay());
        // 5th index is 1st Jan -> selected day in predefined cursor date
        Assert.assertTrue(calendarDays.get(5).isSelected());
    }

    @Test
    public void calendar_day_selected_check(){
        //Arrange
        Date cursorDate = getPredefinedCursorDate(Calendar.JANUARY, 2021);
        SUT.setCursorDate(cursorDate);

        //Act
        SUT.onViewInitialized();
        List<CalendarDay> calendarDays = viewContract.getCalendarDays();
        SUT.onDaySelected(calendarDays, calendarDays.get(14));
        SUT.onDaySelected(calendarDays, calendarDays.get(16));

        //Assert - previous selected day is unselected and new is selected
        Assert.assertFalse(calendarDays.get(14).isSelected());
        Assert.assertTrue(calendarDays.get(16).isSelected());
    }

}
