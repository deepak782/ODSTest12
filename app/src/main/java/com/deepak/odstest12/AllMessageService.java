package com.deepak.odstest12;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class AllMessageService extends FirebaseMessagingService {
    NotificationHelper notificationHelper;
    Class name;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        notificationHelper=new NotificationHelper(this);
        notificationHelper.createChannels(message.getNotification().getTitle(),message.getNotification().getBody(),name);

    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }
}
