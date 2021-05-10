package com.enlife.app.screens.walkthrough;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class PageData implements Serializable {

    private final Drawable pageImage;
    private final String pageText;

    public PageData(Drawable pageImage, String pageText) {
        this.pageImage = pageImage;
        this.pageText = pageText;
    }

    public Drawable getPageImage() {
        return pageImage;
    }

    public String getPageText() {
        return pageText;
    }
}
