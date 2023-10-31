package com.example.myapplication.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.myapplication.R

class Notification(var context: Context, var title: String, var msg: String) {
    val channelId = "FCM001"
    val channelName = "FCMMessage"
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder: NotificationCompat.Builder

    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager



    fun firNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel = NotificationChannel(
                channelId, channelName, NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableVibration(false)
            notificationChannel.enableLights(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.voice)
            setContentTitle(title)
            setContentText(msg)
            setAutoCancel(true)

        }
        notificationManager.notify(100, notificationBuilder.build())
    }
}