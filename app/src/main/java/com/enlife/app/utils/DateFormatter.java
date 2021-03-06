package com.enlife.app.utils;

import android.text.format.DateFormat;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public enum DateFormat {
        INDIAN_DATE_FORMAT("dd/MM/yyyy"),
        MMM_dd_yyyy("MMM dd, yyyy"),
        SINGLE_DIGIT_DAY("d"),
        HH_mm_a("HH:mm a"),
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

    @Nullable
    public Date getDate(DateFormat dateFormat, String strDate) {
        try {
            return new SimpleDateFormat(dateFormat.format, Locale.getDefault()).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public String changeFormat(DateFormat inFormat, DateFormat outFormat, String strDate) {
        try {
            Date date = new SimpleDateFormat(inFormat.format, Locale.getDefault()).parse(strDate);
            return getFormattedDate(outFormat, date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
