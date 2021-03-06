package com.final_project_afeka.android_navigation_final_project.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.final_project_afeka.android_navigation_final_project.NavigationFromNotification;
import com.final_project_afeka.android_navigation_final_project.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

/**
 * Created by idoshapira-mbp on 01/12/2018.
 */

public class MyFirebaseInstanceService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().isEmpty())
        {
            showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }else{
            showNotification(remoteMessage.getData());
        }




    }

    private void showNotification(Map<String, String> data){

        String title = data.get("title").toString();
        String body = data.get("body").toString();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.final_project_afeka.android_navigation_final_project.test";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Missiles Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        //Set Intent

        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, NavigationFromNotification.class);
//      Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
//      Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_notification_warning)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("info")
                .setContentIntent(resultPendingIntent).
                setAutoCancel(true);



        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());


    }

    private void showNotification(String title, String body){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.final_project_afeka.android_navigation_final_project.test";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Missiles Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //Set Intent
        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, NavigationFromNotification.class);
//      Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
//      Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //create notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_notification_warning)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("info").
                setContentIntent(resultPendingIntent).
                setAutoCancel(true);

        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.d("TOKENFIREBASE",s);
    }

}
