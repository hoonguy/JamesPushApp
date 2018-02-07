package com.jh.jamespushapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private static final String TAG = "MyFBMessagingSrvc";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From : " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload : " + remoteMessage.getData());
        }
        sendNotification(remoteMessage.getData().get("message"));
        super.onMessageReceived(remoteMessage);
    }

    private void sendNotification(String messageBody) {
        // Create intent to be performed after notification - at this case, MainActivity called again
        // Change to be display the text view which shows the FCM message
        Intent intentSend = new Intent(this, DisplayMessage.class);
        intentSend.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentSend.putExtra("MessageBody", messageBody);

        // Create pendingintent -> call MainActivity
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intentSend, PendingIntent.FLAG_ONE_SHOT);

        String channelID = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Log.d(TAG, "MessageBody : " + messageBody);

        // Create notification object by NotificationCompat.Builder, object has the 3,
        // setSmallIcon(), setContentTitle(), setContentText()
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)    // clear alarm when user click alarm
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);   // to start activity when user click the alarm contents

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

/* In case of only target to Oreo,

        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(),"id_product")
       .setSmallIcon(R.drawable.flatpnicon) //your app icon
       .setBadgeIconType(R.drawable.flatpnicon) //your app icon
       .setChannelId(id)
       .setContentTitle(extras.get("nt").toString())
       .setAutoCancel(true).setContentIntent(pendingIntent)
       .setNumber(1)
       .setColor(255)
       .setContentText(extras.get("nm").toString())
       .setWhen(System.currentTimeMillis());
        notificationManager.notify(1, notificationBuilder.build());
*/

    }
}
