package com.enlife.app.utils;

import android.text.format.DateFormat;

import java.util.Date;

public class DateFormatter {

    public String getDay(Date date) {
        String dayOfTheWeek = (String) DateFormat.format("EEEE", date); // Thursday
        String day = (String) DateFormat.format("dd", date); // 20
        String monthString = (String) DateFormat.format("MMM", date); // Jun
        String monthNumber = (String) DateFormat.format("MM", date); // 06
        String year = (String) DateFormat.format("yyyy", date);
        return day;
    }

}
