package com.enlife.app.database.operators;

import android.content.Context;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.ApplicationDatabaseHelper;
import com.enlife.app.database.models.Event;

import java.util.List;

public abstract class DatabaseOperator<T extends Object> {

    protected final ApplicationDatabaseHelper databaseHelper;

    public DatabaseOperator(Context context) {
        databaseHelper = new ApplicationDatabaseHelper(context);
    }

    public void closeDatabase() {
        databaseHelper.close();
    }

    public abstract long addData(T data);

    public abstract long addList(List<T> list);

    public abstract int updateData(long id, T data);

    public abstract int deleteData(String... selectorFields);

    public abstract List<T> getList(String... selectorFields);

    public abstract int clearTable();

}
