package com.enlife.app.database.operators;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.enlife.app.database.models.Milestone;
import com.enlife.app.database.models.Quote;
import com.enlife.app.database.tables.MilestoneContract;
import com.enlife.app.database.tables.QuoteContract;

import java.util.ArrayList;
import java.util.List;

public class QuoteDataOperator extends DatabaseOperator<Quote> {

    public QuoteDataOperator(Context context) {
        super(context);
    }

    @Override
    public long addData(Quote data) {
        ContentValues values = new ContentValues();
        values.put(QuoteContract.QuoteEntry.COLUMN_QUOTE, data.getQuote());
        values.put(QuoteContract.QuoteEntry.COLUMN_AUTHOR, data.getAuthor());
        return databaseHelper.getWritableDatabase().insert(QuoteContract.QuoteEntry.TABLE_NAME, null, values);
    }

    @Override
    public void addList(List<Quote> list) {
        for (Quote quote : list) {
            addData(quote);
        }
    }

    @Override
    public int updateData(long id, Quote data) {
        ContentValues values = new ContentValues();
        values.put(QuoteContract.QuoteEntry.COLUMN_QUOTE, data.getQuote());
        values.put(QuoteContract.QuoteEntry.COLUMN_AUTHOR, data.getAuthor());

        String selection = QuoteContract.QuoteEntry._ID + "=?";
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
        String selection = QuoteContract.QuoteEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(selectorFields[0])};
        return databaseHelper.getWritableDatabase()
                .delete(
                        QuoteContract.QuoteEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
    }

    @Override
    public List<Quote> getList(String... selectorFields) {
        String selection = "";

        List<Quote> quotes = new ArrayList<>();

        String[] projection = {
                QuoteContract.QuoteEntry._ID,
                QuoteContract.QuoteEntry.COLUMN_QUOTE,
                QuoteContract.QuoteEntry.COLUMN_AUTHOR
        };

        //paginated response
        if (selectorFields.length > 0) {
            String strRowId = selectorFields[0];
            long rowId = Long.parseLong(strRowId);
            selection = QuoteContract.QuoteEntry._ID + ">" + rowId
                    + " AND " + QuoteContract.QuoteEntry._ID + "<=" + rowId + 5;
        }

        Cursor cursor = databaseHelper.getReadableDatabase().query(
                QuoteContract.QuoteEntry.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                null,
                "5"
        );
        while (cursor.moveToNext()) {
            Quote quote = new Quote(
                    cursor.getLong(cursor.getColumnIndexOrThrow(QuoteContract.QuoteEntry._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(QuoteContract.QuoteEntry.COLUMN_QUOTE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(QuoteContract.QuoteEntry.COLUMN_AUTHOR))
            );
            Log.d(this.getClass().getSimpleName(), "QUOTE ID:" + quote.getQuoteId());
            quotes.add(quote);
        }
        cursor.close();
        return quotes;
    }

    @Override
    public int clearTable() {
        return databaseHelper.getWritableDatabase().delete(QuoteContract.QuoteEntry.TABLE_NAME, null, null);
    }
}
