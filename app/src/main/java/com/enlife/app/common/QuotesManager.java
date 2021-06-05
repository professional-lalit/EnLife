package com.enlife.app.common;

import android.content.Context;

import com.enlife.app.R;
import com.enlife.app.database.models.Quote;
import com.enlife.app.database.operators.DatabaseOperator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuotesManager {

    private Context context;
    private DatabaseOperator<Quote> databaseOperator;

    public QuotesManager(Context context, DatabaseOperator<Quote> databaseOperator) {
        this.context = context;
        this.databaseOperator = databaseOperator;
    }

    private String readText() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.quotes);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while ((line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }

    public List<Quote> getQuotes(long rowId) {
        List<Quote> quotes = databaseOperator.getList(String.valueOf(rowId));
        if (quotes.isEmpty()) {
            String strData = readText();
            Type type = new TypeToken<ArrayList<Quote>>() {
            }.getType();
            List<Quote> quotesFromFile = Objects.requireNonNull(new Gson().fromJson(strData, type));
            databaseOperator.addList(quotesFromFile);
            return databaseOperator.getList(String.valueOf(rowId));
        }
        return quotes;
    }


}
