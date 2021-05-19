package com.enlife.app.utils;

import android.widget.Toast;

import com.enlife.app.common.CustomApplication;

public class Utils {
    public static void showToast(String text) {
        Toast.makeText(CustomApplication.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(String text) {
        Toast.makeText(CustomApplication.getAppContext(), text, Toast.LENGTH_LONG).show();
    }
}
