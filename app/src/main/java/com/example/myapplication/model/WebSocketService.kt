package com.example.myapplication.model

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.myapplication.R
import com.example.myapplication.manager.WebSocketManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.concurrent.thread
import kotlin.random.Random

@AndroidEntryPoint
class WebSocketService : Service() {
    @Inject
    lateinit var webSocketManager: WebSocketManager

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate() {
        super.onCreate()
        webSocketManager.messageLiveData.observeForever {
            showNotification(it)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    /**
     *  顯示通知
     *
     *  @param  message 訊息
     *
     *  @author Ashley
     */
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
        scope.launch {
            webSocketManager.close()
        }
        job.cancel()
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotificationChannel()
        thread {
            while (true) {
                Log.i("Ashley-logService", "Service is running...")
                try {
                    TimeUnit.SECONDS.sleep(2)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val channelId = "service channel"
            val channelName = "service in foreground"
            val channel: NotificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.description = "foreground Service"
            notificationManager.createNotificationChannel(channel)


            val notifyId = Random.nextInt(1, 100)
            val notification: Notification = Notification.Builder(this, channelId).apply {
                setContentTitle("服務執行")
                setContentText("背景執行中...")
            }.build()
            startForeground(notifyId, notification)
        }
    }
}