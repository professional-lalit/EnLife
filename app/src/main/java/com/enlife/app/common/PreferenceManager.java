package com.enlife.app.common;

import android.content.SharedPreferences;

public class PreferenceManager {

    public static final String PREF_NAME = "EnLife";

    public static final String DAILY_WAKEUP_NOTIFICATION_SET = "daily_wakeup_notification_set";

    private final SharedPreferences sharedPreferences;

    public PreferenceManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setDailyWakeupNotification(boolean isSet) {
        sharedPreferences.edit().putBoolean(DAILY_WAKEUP_NOTIFICATION_SET, isSet).apply();
    }

    public boolean isDailyWakeupNotificationSet() {
        return sharedPreferences.getBoolean(DAILY_WAKEUP_NOTIFICATION_SET, false);
    }

}
