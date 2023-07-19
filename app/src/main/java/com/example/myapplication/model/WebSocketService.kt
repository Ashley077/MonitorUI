package com.example.myapplication.model

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.manager.WebSocketManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import javax.inject.Inject

@AndroidEntryPoint
class WebSocketService : Service() {
    @Inject lateinit var webSocketManager: WebSocketManager

    override fun onCreate() {
        super.onCreate()
        webSocketManager.messageLiveData.observeForever {
            showNotification(it)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private fun showNotification(message: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationId = 2
        val notification = NotificationCompat.Builder(this, "WebSocketChannel")
            .setContentTitle("New Message")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        notificationManager.notify(notificationId, notification)
    }
    override fun onDestroy() {
        webSocketManager.close()
        super.onDestroy()
    }
}