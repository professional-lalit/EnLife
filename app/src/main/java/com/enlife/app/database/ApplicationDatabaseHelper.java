package com.enlife.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.enlife.app.database.tables.EventContract;
import com.enlife.app.database.tables.GoalContract;
import com.enlife.app.database.tables.MilestoneContract;

public class ApplicationDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "EnLife.db";

    public ApplicationDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EventContract.getCreateQuery());
        db.execSQL(GoalContract.getCreateQuery());
        db.execSQL(MilestoneContract.getCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
