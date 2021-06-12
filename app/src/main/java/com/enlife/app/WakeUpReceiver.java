package com.enlife.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.enlife.app.common.CustomApplication;
import com.enlife.app.database.operators.QuoteDataOperator;
import com.enlife.app.utils.NotificationHelper;

import javax.inject.Inject;

public class WakeUpReceiver extends BroadcastReceiver {

    @Inject
    QuoteDataOperator quoteDataOperator;

    @Override
    public void onReceive(Context context, Intent intent) {

        CustomApplication.getInstance()
                .applicationComponent
                .inject(this);

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getWakeUpNotification(
                "Wake Up! It's time to follow your regimen",
                quoteDataOperator.getRandomQuoteForWakeUp()
        );
        notificationHelper.getManager().notify(1, nb.build());
    }
}
