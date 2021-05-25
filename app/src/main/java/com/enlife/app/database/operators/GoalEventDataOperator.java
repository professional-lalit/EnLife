package com.enlife.app.database.operators;

import android.content.Context;

import com.enlife.app.database.models.Event;

import java.util.List;

public class GoalEventDataOperator extends DatabaseOperator {

    public GoalEventDataOperator(Context context) {
        super(context);
    }

    @Override
    public long addData(Object data) {
        return 0;
    }

    @Override
    public long addList(List<Object> list) {
        return 0;
    }

    @Override
    public int updateData(long id, Object data) {
        return 0;
    }

    @Override
    public int deleteData(String... selectorFields) {
        return 0;
    }

    @Override
    public List<Event> getList(String... selectorFields) {
        return null;
    }

    @Override
    public int clearTable() {
        return 0;
    }
}
