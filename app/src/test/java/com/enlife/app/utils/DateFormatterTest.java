package com.enlife.app.utils;

import com.enlife.app.utils.DateFormatter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class DateFormatterTest {

    private DateFormatter SUT = new DateFormatter();

    @Test
    public void single_digit_day() {
        //Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        Date todayDate = calendar.getTime();
        //Act
        String day = SUT.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_DAY, todayDate);
        //Assert
        Assert.assertEquals("20", day);
    }

    @Test
    public void single_digit_month() {
        //Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 5);
        Date todayDate = calendar.getTime();
        //Act
        String day = SUT.getFormattedDate(DateFormatter.DateFormat.SINGLE_DIGIT_MONTH, todayDate);
        //Assert
        Assert.assertEquals("6", day);
    }

    @Test
    public void full_name_month() {
        //Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 5);
        Date todayDate = calendar.getTime();
        //Act
        String day = SUT.getFormattedDate(DateFormatter.DateFormat.FULL_NAME_MONTH, todayDate);
        //Assert
        Assert.assertEquals("June", day);
    }

    @Test
    public void indian_date_format() {
        //Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 20);
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.YEAR, 2021);
        Date todayDate = calendar.getTime();
        //Act
        String day = SUT.getFormattedDate(DateFormatter.DateFormat.INDIAN_DATE_FORMAT, todayDate);
        //Assert
        Assert.assertEquals("20/06/2021", day);
    }

}
