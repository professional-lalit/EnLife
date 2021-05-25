package com.enlife.app.database.operators;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import androidx.annotation.Nullable;

import com.enlife.app.database.models.DateEventCount;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.tables.EventContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EventDataOperator extends DatabaseOperator {

    @Inject
    public EventDataOperator(Context context) {
        super(context);
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

    @Override
    public long addData(Object data) {
        Event event = (Event) data;
        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_EVENT_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_EVENT_DESCRIPTION, event.getDescription());
        values.put(EventContract.EventEntry.COLUMN_EVENT_IS_ALL_DAY, event.isAllDay());
        values.put(EventContract.EventEntry.COLUMN_EVENT_LOCATION, event.getLocation());
        values.put(EventContract.EventEntry.COLUMN_EVENT_REPEAT_MODE, event.getRepeatMode().getMode());
        values.put(EventContract.EventEntry.COLUMN_EVENT_FROM_TIME, event.getFromTime());
        values.put(EventContract.EventEntry.COLUMN_EVENT_TO_TIME, event.getToTime());
        values.put(EventContract.EventEntry.COLUMN_EVENT_IMAGE_PATH, event.getImagePath());
        values.put(EventContract.EventEntry.COLUMN_EVENT_DATE, event.getDate());
        return databaseHelper.getWritableDatabase().insert(EventContract.EventEntry.TABLE_NAME, null, values);
    }

    @Override
    public long addList(List<Object> list) {
        return 0;
    }


    @Override
    public int updateData(long id, Object data) {
        Event event = (Event) data;
        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_EVENT_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_EVENT_DESCRIPTION, event.getDescription());
        values.put(EventContract.EventEntry.COLUMN_EVENT_IS_ALL_DAY, event.isAllDay());
        values.put(EventContract.EventEntry.COLUMN_EVENT_LOCATION, event.getLocation());
        values.put(EventContract.EventEntry.COLUMN_EVENT_REPEAT_MODE, event.getRepeatMode().getMode());
        values.put(EventContract.EventEntry.COLUMN_EVENT_FROM_TIME, event.getFromTime());
        values.put(EventContract.EventEntry.COLUMN_EVENT_TO_TIME, event.getToTime());

        if (event.getImagePath() != null && !event.getImagePath().isEmpty()) {
            values.put(EventContract.EventEntry.COLUMN_EVENT_IMAGE_PATH, event.getImagePath().isEmpty());
        }

        String selection = EventContract.EventEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        return databaseHelper.getWritableDatabase().update(
                EventContract.EventEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public final List<Event> getList(String... selectorFields) {

        String selection = "";
        String[] selectionArgs = null;

        List<Event> events = new ArrayList<>();
        String[] projection = {
                EventContract.EventEntry._ID,
                EventContract.EventEntry.COLUMN_EVENT_TITLE,
                EventContract.EventEntry.COLUMN_EVENT_DESCRIPTION,
                EventContract.EventEntry.COLUMN_EVENT_DATE,
                EventContract.EventEntry.COLUMN_EVENT_IS_ALL_DAY,
                EventContract.EventEntry.COLUMN_EVENT_LOCATION,
                EventContract.EventEntry.COLUMN_EVENT_REPEAT_MODE,
                EventContract.EventEntry.COLUMN_EVENT_FROM_TIME,
                EventContract.EventEntry.COLUMN_EVENT_TO_TIME,
                EventContract.EventEntry.COLUMN_EVENT_IMAGE_PATH,
        };

        if (selectorFields.length > 0) {
            String date = selectorFields[0];
            selectionArgs = new String[]{date};
            selection = EventContract.EventEntry.COLUMN_EVENT_DATE + "=?";
        }

        Cursor cursor = databaseHelper.getReadableDatabase().query(
                EventContract.EventEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );


        while (cursor.moveToNext()) {
            Event event = new Event(
                    cursor.getLong(cursor.getColumnIndexOrThrow(EventContract.EventEntry._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_DATE)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_IS_ALL_DAY)) == 1,
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_LOCATION)),
                    getEventRepeatMode(cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_REPEAT_MODE))),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_FROM_TIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_TO_TIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(EventContract.EventEntry.COLUMN_EVENT_IMAGE_PATH))
            );
            events.add(event);
        }
        cursor.close();
        return events;
    }

    public List<DateEventCount> getDateEventCounts() {
        List<DateEventCount> dateEventCountList = new ArrayList<>();
        String SQL = "select date, count(*) as events_for_day from events group by date";

        Cursor cursor = databaseHelper.getReadableDatabase().rawQuery(SQL, null);
        while (cursor.moveToNext()) {
            String date = cursor.getString(0);
            int eventCount = cursor.getInt(1);
            dateEventCountList.add(new DateEventCount(date, eventCount));
        }
        cursor.close();
        return dateEventCountList;
    }

    @Override
    public int clearTable() {
        return databaseHelper.getWritableDatabase().delete(EventContract.EventEntry.TABLE_NAME, null, null);
    }

    @Override
    public final int deleteData(String... selectorFields) {
        String selection = EventContract.EventEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(selectorFields[0])};
        return databaseHelper.getWritableDatabase()
                .delete(
                        EventContract.EventEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
    }
}
