package com.example.android.firebasemessage

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagineService : FirebaseMessagingService() {
    companion object {
        private val TAG = MyFirebaseMessagineService::class.java.simpleName
    }

    override fun onNewToken(token: String?) {
        Log.e(TAG, "$token")
        // ePdJaJrFVcU:APA91bGrT-1vAM-7aDI1FFB5psMYJhmLwEXz2ZJ_zbi3d3WDKuKJtvRYnR723zqAapWyQBHaUcq9koYVkl4WiPrwEu1yhlgdNXrKruFOLczmJHKLu_Kq_JquXUqFv6ONK-kvXvX6VWpY

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        sendNotification(remoteMessage)
    }

    private fun sendNotification(remoteMessage: RemoteMessage?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage?.notification?.title)
                .setContentText(remoteMessage?.notification?.body)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())

    }
}

// Thanks for watching this video . Please subscribe my channel thanks again  :P