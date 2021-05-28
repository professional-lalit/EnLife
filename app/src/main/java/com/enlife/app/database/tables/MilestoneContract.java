package com.enlife.app.database.tables;

import android.provider.BaseColumns;

public class MilestoneContract {
    private MilestoneContract() {

    }

    public static String getCreateQuery() {
        return "CREATE TABLE " + MilestoneEntry.TABLE_NAME + " (" +
                MilestoneEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                MilestoneEntry.COLUMN_MILESTONE_TITLE + " TEXT," +
                MilestoneEntry.COLUMN_MILESTONE_DESCRIPTION + " TEXT," +
                MilestoneEntry.COLUMN_MILESTONE_FROM_DATE + " VARCHAR(20)," +
                MilestoneEntry.COLUMN_MILESTONE_TO_DATE + " VARCHAR(20))";
    }

    public static class MilestoneEntry implements BaseColumns {
        public static final String TABLE_NAME = "milestones";

        public static final String COLUMN_MILESTONE_TITLE = "title";
        public static final String COLUMN_MILESTONE_DESCRIPTION = "description";
        public static final String COLUMN_MILESTONE_FROM_DATE = "from_date";
        public static final String COLUMN_MILESTONE_TO_DATE = "to_date";
        public static final String COLUMN_MILESTONE_GOAL_ID = "goal_id";
    }
}
