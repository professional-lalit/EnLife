package com.enlife.app.database.operators;

import android.content.Context;

import java.util.List;

/**
 * This class is used to reduce verbosity by implementing methods here
 * @param <T>
 */
public class DatabaseOperatorAdapter<T extends Object> extends DatabaseOperator<T> {

    public DatabaseOperatorAdapter(Context context) {
        super(context);
    }

    @Override
    public long addData(T data) {
        return 0;
    }

    @Override
    public void addList(List<T> list) {

    }

    @Override
    public int updateData(long id, T data) {
        return 0;
    }

    @Override
    public int deleteData(String... selectorFields) {
        return 0;
    }

    @Override
    public List<T> getList(String... selectorFields) {
        return null;
    }

    @Override
    public int clearTable() {
        return 0;
    }
}
