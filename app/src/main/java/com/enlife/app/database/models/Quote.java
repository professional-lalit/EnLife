package com.enlife.app.database.models;

import com.google.gson.annotations.SerializedName;

public class Quote {
    private long quoteId;
    @SerializedName("text")
    private final String quote;
    private final String author;

    public Quote(long quoteId, String quote, String author) {
        this.quoteId = quoteId;
        this.quote = quote;
        this.author = author;
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
    }

    public long getQuoteId() {
        return quoteId;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}
