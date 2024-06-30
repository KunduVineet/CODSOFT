package com.teeniv.alarm_manager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent nextActivity = new Intent(context,Notification_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,nextActivity, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder  builder = new NotificationCompat.Builder(context,"alarm")
                .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                .setContentTitle("Reminder")
                .setContentText("hey, It;s time to wake up")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
    }
}
