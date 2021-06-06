package com.enlife.app.database.tables;

import android.provider.BaseColumns;

public class GoalEventContract {

    public static class GoalEventEntry implements BaseColumns {
        public static final String GOAL_TABLE_NAME = "goals";
        public static final String EVENT_TABLE_NAME = "events";

        public static final String COLUMN_GOAL_TITLE = "title";
        public static final String COLUMN_GOAL_DESCRIPTION = "description";
        public static final String COLUMN_GOAL_TYPE = "type";
        public static final String COLUMN_GOAL_FROM_DATE = "from_date";
        public static final String COLUMN_GOAL_TO_DATE = "to_date";

        public static final String COLUMN_EVENT_TITLE = "title";
        public static final String COLUMN_EVENT_DESCRIPTION = "description";
        public static final String COLUMN_EVENT_DATE = "date";
        public static final String COLUMN_EVENT_IS_ALL_DAY = "is_all_day";
        public static final String COLUMN_EVENT_LOCATION = "location";
        public static final String COLUMN_EVENT_REPEAT_MODE = "repeat_mode";
        public static final String COLUMN_EVENT_FROM_TIME = "from_time";
        public static final String COLUMN_EVENT_TO_TIME = "to_time";
        public static final String COLUMN_EVENT_IMAGE_PATH = "image_path";
        public static final String COLUMN_EVENT_GOAL_ID = "goal_id";
        public static final String COLUMN_EVENT_MILESTONE_ID = "milestone_id";
    }
}
