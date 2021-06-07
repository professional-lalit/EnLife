package com.enlife.app.database.operators;

import android.content.Context;
import android.database.Cursor;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.models.Goal;
import com.enlife.app.database.models.GoalEvent;
import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.tables.EventContract;
import com.enlife.app.database.tables.GoalContract;
import com.enlife.app.database.tables.MilestoneContract;

import java.util.ArrayList;
import java.util.List;

public class GoalEventDataOperator extends DatabaseOperatorAdapter<GoalEvent> {

    public GoalEventDataOperator(Context context) {
        super(context);
    }

    @Override
    public List<GoalEvent> getList(String... selectorFields) {
        List<GoalEvent> goalEvents = new ArrayList<>();
        String strDate = selectorFields[0];

        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery(
                "SELECT a._id as eventId, a.title, a.description, a.date, a.is_all_day, a.location, a.repeat_mode, " +
                        "a.from_time, a.to_time, a.image_path, a.goal_id, a.milestone_id, " +
                        "b._id, b.title, b.description, b.type, b.from_date, b.to_date, " +
                        "c._id, c.title, c.description, c.from_date, c.to_date " +
                        "FROM events as a, goals as b, milestones as c " +
                        "WHERE a.goal_id=b._id AND a.milestone_id=c._id AND a.date='" + strDate + "'",
                null
        );
        while (cursor.moveToNext()) {
            Event event = new Event(
                    cursor.getLong(cursor.getColumnIndexOrThrow("eventId")),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_DATE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_IS_ALL_DAY)) == 1,
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_LOCATION)),
                    getEventRepeatMode(cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_REPEAT_MODE))),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_FROM_TIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_TO_TIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_IMAGE_PATH)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_GOAL_ID)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_MILESTONE_ID))
            );

            Milestone milestone = new Milestone(
                    cursor.getLong(cursor.getColumnIndexOrThrow("milestoneId")),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_FROM_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TO_DATE)),
                    null,
                    cursor.getLong(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_GOAL_ID))
            );

            Goal goal = new Goal(
                    cursor.getLong(cursor.getColumnIndexOrThrow("goalId")),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_DESCRIPTION)),
                    getGoalType(cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_TYPE))),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_FROM_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_TO_DATE)),
                    null
            );
            goalEvents.add(new GoalEvent(event, goal, milestone));
        }
        cursor.close();
        return goalEvents;
    }

    private Goal.GoalType getGoalType(String type) {
        if (type.equals(Goal.GoalType.WEEKLY.getType())) {
            return Goal.GoalType.WEEKLY;
        } else if (type.equals(Goal.GoalType.MONTHLY.getType())) {
            return Goal.GoalType.MONTHLY;
        } else if (type.equals(Goal.GoalType.ANNUAL.getType())) {
            return Goal.GoalType.ANNUAL;
        } else {
            return Goal.GoalType.MONTHLY;
        }
    }

    private Event.RepeatMode getEventRepeatMode(String string) {
        if (string.equals(Event.RepeatMode.DAILY.getMode())) {
            return Event.RepeatMode.DAILY;
        } else if (string.equals(Event.RepeatMode.WEEKLY.getMode())) {
            return Event.RepeatMode.WEEKLY;
        } else if (string.equals(Event.RepeatMode.MONTHLY.getMode())) {
            return Event.RepeatMode.MONTHLY;
        } else {
            return Event.RepeatMode.NONE;
        }
    }
}