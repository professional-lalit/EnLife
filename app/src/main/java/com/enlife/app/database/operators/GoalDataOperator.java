package com.enlife.app.database.operators;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.enlife.app.database.models.Goal;
import com.enlife.app.database.tables.GoalContract;

import java.util.ArrayList;
import java.util.List;

public class GoalDataOperator extends DatabaseOperator<Goal> {

    public GoalDataOperator(Context context) {
        super(context);
    }

    @Override
    public long addData(Goal data) {
        ContentValues values = new ContentValues();
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_TITLE, data.getTitle());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_DESCRIPTION, data.getDescription());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_TYPE, data.getGoalType());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_FROM_DATE, data.getFromDate());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_TO_DATE, data.getToDate());
        return databaseHelper.getWritableDatabase().insert(GoalContract.GoalEntry.TABLE_NAME, null, values);
    }

    @Override
    public void addList(List<Goal> list) {
        for (Goal goal : list) {
            addData(goal);
        }
    }

    @Override
    public int updateData(long id, Goal data) {
        ContentValues values = new ContentValues();
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_TITLE, ((Goal) data).getTitle());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_DESCRIPTION, ((Goal) data).getDescription());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_TYPE, ((Goal) data).getGoalType());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_FROM_DATE, ((Goal) data).getFromDate());
        values.put(GoalContract.GoalEntry.COLUMN_GOAL_TO_DATE, ((Goal) data).getToDate());

        String selection = GoalContract.GoalEntry.COLUMN_GOAL_TITLE + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        return databaseHelper.getWritableDatabase().update(
                GoalContract.GoalEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public int deleteData(String... selectorFields) {
        String selection = GoalContract.GoalEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(selectorFields[0])};
        return databaseHelper.getWritableDatabase()
                .delete(
                        GoalContract.GoalEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
    }

    @Override
    public List<Goal> getList(String... selectorFields) {
        String selection = "";
        String[] selectionArgs = null;

        List<Goal> goals = new ArrayList<>();

        String[] projection = {
                GoalContract.GoalEntry._ID,
                GoalContract.GoalEntry.COLUMN_GOAL_TITLE,
                GoalContract.GoalEntry.COLUMN_GOAL_DESCRIPTION,
                GoalContract.GoalEntry.COLUMN_GOAL_TYPE,
                GoalContract.GoalEntry.COLUMN_GOAL_FROM_DATE,
                GoalContract.GoalEntry.COLUMN_GOAL_TO_DATE,
        };

        if (selectorFields.length > 0) {
            String date = selectorFields[0];
            selectionArgs = new String[]{date};
            selection = GoalContract.GoalEntry.COLUMN_GOAL_TITLE + "like ?";
        }

        Cursor cursor = databaseHelper.getReadableDatabase().query(
                GoalContract.GoalEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            Goal goal = new Goal(
                    cursor.getLong(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_DESCRIPTION)),
                    getGoalType(cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_TYPE))),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_FROM_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(GoalContract.GoalEntry.COLUMN_GOAL_TO_DATE)),
                    null
            );
            goals.add(goal);
        }
        cursor.close();
        return goals;
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

    @Override
    public int clearTable() {
        return databaseHelper.getWritableDatabase().delete(GoalContract.GoalEntry.TABLE_NAME, null, null);
    }
}
