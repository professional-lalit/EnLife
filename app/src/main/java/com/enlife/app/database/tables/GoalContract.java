package com.enlife.app.database.tables;

import android.provider.BaseColumns;

public class GoalContract {
    private GoalContract() {

    }

    public static String getCreateQuery() {
        return "CREATE TABLE " + GoalEntry.TABLE_NAME + " (" +
                EventContract.EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                GoalEntry.COLUMN_GOAL_TITLE + " TEXT," +
                GoalEntry.COLUMN_GOAL_DESCRIPTION + " TEXT," +
                GoalEntry.COLUMN_GOAL_TYPE + " VARCHAR(20)," +
                GoalEntry.COLUMN_GOAL_FROM_DATE + " VARCHAR(100)," +
                GoalEntry.COLUMN_GOAL_TO_DATE + " VARCHAR(20))";
    }

    public static class GoalEntry implements BaseColumns {
        public static final String TABLE_NAME = "goals";

        public static final String COLUMN_GOAL_TITLE = "title";
        public static final String COLUMN_GOAL_DESCRIPTION = "description";
        public static final String COLUMN_GOAL_TYPE = "type";
        public static final String COLUMN_GOAL_FROM_DATE = "from_date";
        public static final String COLUMN_GOAL_TO_DATE = "to_date";
    }
}
