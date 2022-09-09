package com.deepak.odstest12;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper extends ContextWrapper {
    Class name;

    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            createChannels("","",name);
        }
    }

    public void createChannels(String title, String body, Class name) {

        String channelID="MYChannelID";
        String channelName="My New Notification Channel";
        NotificationChannel notificationChannel=new NotificationChannel(channelID,channelName, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager notificationManager=getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
        Intent intent1=new Intent(this,name);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,intent1,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder=new Notification.Builder(this,channelID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_baseline_agriculture_24)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                ;
        NotificationManagerCompat.from(this).notify(1,builder.build());

    }
}
