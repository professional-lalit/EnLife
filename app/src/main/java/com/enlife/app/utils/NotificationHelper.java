package com.enlife.app.utils;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.enlife.app.R;
import com.enlife.app.database.models.Quote;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Alarm!")
                .setContentText("Your AlarmManager is working.")
                .setSmallIcon(R.drawable.ic_notification_alarm);
    }

    public NotificationCompat.Builder getWakeUpNotification(String title, Quote quote) {
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.layout_small_notification);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.layout_notification_expanded);

        notificationLayoutExpanded.setTextViewText(R.id.txt_notification_title, title);
        notificationLayoutExpanded.setTextViewText(R.id.txt_quote, quote.getQuote());
        notificationLayoutExpanded.setTextViewText(R.id.txt_author, quote.getAuthor());

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setSmallIcon(R.drawable.ic_notification_alarm)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded);
    }
}
