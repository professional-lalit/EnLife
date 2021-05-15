package com.enlife.app.database.operators;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.ApplicationDatabaseHelper;
import com.enlife.app.database.models.Event;
import com.enlife.app.database.tables.EventContract;

import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseOperator {

    protected final ApplicationDatabaseHelper databaseHelper;

    public DatabaseOperator() {
        Context context = CustomApplication.getAppContext();
        databaseHelper = new ApplicationDatabaseHelper(context);
    }

    protected abstract <T> long addData(T data);

    protected abstract <T> int updateData(long id, T data);

    protected abstract <T,R> List<R> getList(T... selectorFields);

    protected abstract <T> int deleteData(T... selectorFields);
}
