package com.enlife.app.database.operators;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.annotation.NonNull;

import com.enlife.app.database.models.Event;
import com.enlife.app.database.tables.EventContract;

import java.util.ArrayList;
import java.util.List;

public class EventDataOperator extends DatabaseOperator {

    public List<Event> getEvents(String date) {
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
        String selection = EventContract.EventEntry.COLUMN_EVENT_DATE + " = ?";
        String[] selectionArgs = {date};

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
    protected <T> long addData(T data) {
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
        return databaseHelper.getWritableDatabase().insert(EventContract.EventEntry.TABLE_NAME, null, values);
    }

    @Override
    protected <T> int updateData(long id, T data) {
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
                selectionArgs);
    }

    @SafeVarargs
    @Override
    protected final <T, R> List<R> getList(T... selectorFields) {
        String date = (String) selectorFields[0];
        List<R> events = new ArrayList<>();
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
        String selection = EventContract.EventEntry.COLUMN_EVENT_DATE + " = ?";
        String[] selectionArgs = {date};

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
            events.add((R) event);
        }
        cursor.close();
        return events;
    }

    @SafeVarargs
    @Override
    protected final <T> int deleteData(T... selectorFields) {
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
