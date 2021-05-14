package com.enlife.app.utils;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public enum DateFormat {
        REGULAR_INDIAN_DATE("dd/MM/yyyy"),
        SINGLE_DIGIT_DAY("d"),
        SINGLE_DIGIT_MONTH("M"),
        FULL_NAME_MONTH("MMMM");

        private final String format;

        DateFormat(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public String getFormattedDate(DateFormat dateFormat, Date date) {
        return new SimpleDateFormat(dateFormat.format, Locale.getDefault())
                .format(date);
    }

}
