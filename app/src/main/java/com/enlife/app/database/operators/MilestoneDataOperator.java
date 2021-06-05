package com.enlife.app.database.operators;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.tables.MilestoneContract;

import java.util.ArrayList;
import java.util.List;

public class MilestoneDataOperator extends DatabaseOperator<Milestone> {

    public MilestoneDataOperator(Context context) {
        super(context);
    }

    @Override
    public long addData(Milestone data) {
        ContentValues values = new ContentValues();
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TITLE, data.getTitle());
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_DESCRIPTION, data.getDescription());
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_FROM_DATE, data.getFromDate());
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TO_DATE, data.getToDate());
        return databaseHelper.getWritableDatabase().insert(MilestoneContract.MilestoneEntry.TABLE_NAME, null, values);
    }

    @Override
    public void addList(List<Milestone> list) {
        for (Milestone milestone : list) {
            addData(milestone);
        }
    }

    @Override
    public int updateData(long id, Milestone data) {
        ContentValues values = new ContentValues();
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TITLE, data.getTitle());
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_DESCRIPTION, data.getDescription());
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_FROM_DATE, data.getFromDate());
        values.put(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TO_DATE, data.getToDate());

        String selection = MilestoneContract.MilestoneEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        return databaseHelper.getWritableDatabase().update(
                MilestoneContract.MilestoneEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public int deleteData(String... selectorFields) {
        String selection = MilestoneContract.MilestoneEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(selectorFields[0])};
        return databaseHelper.getWritableDatabase()
                .delete(
                        MilestoneContract.MilestoneEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
    }

    @Override
    public List<Milestone> getList(String... selectorFields) {
        String selection = "";
        String[] selectionArgs = null;

        List<Milestone> milestones = new ArrayList<>();

        String[] projection = {
                MilestoneContract.MilestoneEntry._ID,
                MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TITLE,
                MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_DESCRIPTION,
                MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_FROM_DATE,
                MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TO_DATE,
                MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_GOAL_ID,
        };

        if (selectorFields.length > 0) {
            String date = selectorFields[0];
            selectionArgs = new String[]{date};
            selection = MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TITLE + "like ?";
        }

        Cursor cursor = databaseHelper.getReadableDatabase().query(
                MilestoneContract.MilestoneEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            Milestone milestone = new Milestone(
                    cursor.getLong(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_FROM_DATE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_TO_DATE)),
                    null,
                    cursor.getLong(cursor.getColumnIndexOrThrow(MilestoneContract.MilestoneEntry.COLUMN_MILESTONE_GOAL_ID))
            );
            milestones.add(milestone);
        }
        cursor.close();
        return milestones;
    }

    @Override
    public int clearTable() {
        return databaseHelper.getWritableDatabase().delete(MilestoneContract.MilestoneEntry.TABLE_NAME, null, null);
    }


}
