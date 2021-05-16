package com.enlife.app.database.operators;

import android.content.Context;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.ApplicationDatabaseHelper;
import com.enlife.app.database.models.Event;

import java.util.List;

public abstract class DatabaseOperator {

    protected final ApplicationDatabaseHelper databaseHelper;

    public DatabaseOperator() {
        Context context = CustomApplication.getAppContext();
        databaseHelper = new ApplicationDatabaseHelper(context);
    }

    public void closeDatabase() {
        databaseHelper.close();
    }

    public abstract long addData(Object data);

    public abstract int updateData(long id, Object data);

    public abstract int deleteData(String... selectorFields);

    public abstract List<Event> getList(String... selectorFields);

    public abstract int clearTable();

}
