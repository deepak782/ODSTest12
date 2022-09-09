package com.deepak.odstest12;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessageService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

        //dataplayload/notification
        String title=message.getNotification().getTitle();
        String body=message.getNotification().getBody();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            String channelID="ChannelID";
            String channelName="My Notification Channel";
            NotificationChannel notificationChannel=new NotificationChannel(channelID,channelName, NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

            Notification.Builder builder=new Notification.Builder(this,channelID)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(R.drawable.ic_baseline_agriculture_24)
                    .setAutoCancel(true);
            NotificationManagerCompat.from(this).notify(1,builder.build());

        }

        super.onMessageReceived(message);
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

    }
}
