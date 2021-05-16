package com.enlife.app.database.tables;

import android.provider.BaseColumns;

public class EventContract {

    private EventContract(){

    }

    public static String getCreateQuery(){
        return "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
                EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                EventEntry.COLUMN_EVENT_TITLE + " TEXT," +
                EventEntry.COLUMN_EVENT_DESCRIPTION + " TEXT," +
                EventEntry.COLUMN_EVENT_IS_ALL_DAY + " INTEGER," +
                EventEntry.COLUMN_EVENT_LOCATION + " VARCHAR(100)," +
                EventEntry.COLUMN_EVENT_REPEAT_MODE + " VARCHAR(20)," +
                EventEntry.COLUMN_EVENT_FROM_TIME + " VARCHAR(10)," +
                EventEntry.COLUMN_EVENT_TO_TIME + " VARCHAR(10)," +
                EventEntry.COLUMN_EVENT_DATE + " VARCHAR(10)," +
                EventEntry.COLUMN_EVENT_IMAGE_PATH + " TEXT)";
    }

    public static class EventEntry implements BaseColumns{
        public static final String TABLE_NAME = "events";

        public static final String COLUMN_EVENT_TITLE = "title";
        public static final String COLUMN_EVENT_DESCRIPTION = "description";
        public static final String COLUMN_EVENT_DATE = "date";
        public static final String COLUMN_EVENT_IS_ALL_DAY = "is_all_day";
        public static final String COLUMN_EVENT_LOCATION = "location";
        public static final String COLUMN_EVENT_REPEAT_MODE = "repeat_mode";
        public static final String COLUMN_EVENT_FROM_TIME = "from_time";
        public static final String COLUMN_EVENT_TO_TIME = "to_time";
        public static final String COLUMN_EVENT_IMAGE_PATH = "image_path";
    }

}
