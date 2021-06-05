package com.enlife.app.database.tables;

import android.provider.BaseColumns;

public class QuoteContract {
    private QuoteContract() {
    }

    public static String getCreateQuery() {
        return "CREATE TABLE " + QuoteContract.QuoteEntry.TABLE_NAME + " (" +
                QuoteContract.QuoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                QuoteContract.QuoteEntry.COLUMN_QUOTE + " TEXT," +
                QuoteContract.QuoteEntry.COLUMN_AUTHOR + " VARCHAR(30))";
    }

    public static class QuoteEntry implements BaseColumns {
        public static final String TABLE_NAME = "quotes";

        public static final String COLUMN_QUOTE = "quote";
        public static final String COLUMN_AUTHOR = "author";
    }
}
