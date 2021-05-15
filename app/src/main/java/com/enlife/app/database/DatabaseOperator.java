package com.enlife.app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.tables.EventContract;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOperator {

    private final SQLiteDatabase writeableDatabase;
    private final SQLiteDatabase readableDatabase;

    public DatabaseOperator() {
        Context context = CustomApplication.getAppContext();
        ApplicationDatabaseHelper databaseHelper = new ApplicationDatabaseHelper(context);
        writeableDatabase = databaseHelper.getWritableDatabase();
        readableDatabase = databaseHelper.getReadableDatabase();
    }

    public Long addEvent(Event event) {
        ContentValues values = new ContentValues();
        values.put(EventContract.EventEntry.COLUMN_EVENT_TITLE, event.getTitle());
        values.put(EventContract.EventEntry.COLUMN_EVENT_DESCRIPTION, event.getDescription());
        values.put(EventContract.EventEntry.COLUMN_EVENT_IS_ALL_DAY, event.isAllDay());
        values.put(EventContract.EventEntry.COLUMN_EVENT_LOCATION, event.getLocation());
        values.put(EventContract.EventEntry.COLUMN_EVENT_REPEAT_MODE, event.getRepeatMode().getMode());
        values.put(EventContract.EventEntry.COLUMN_EVENT_FROM_TIME, event.getFromTime());
        values.put(EventContract.EventEntry.COLUMN_EVENT_TO_TIME, event.getToTime());
        values.put(EventContract.EventEntry.COLUMN_EVENT_IMAGE_PATH, event.getImagePath());
        return writeableDatabase.insert(EventContract.EventEntry.TABLE_NAME, null, values);
    }

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

        Cursor cursor = readableDatabase.query(
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

}
