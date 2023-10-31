package com.example.myapplication.viewmodel

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BuildConfig
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.manager.WebSocketManager
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * WebSocketViewModel 連線功能
 *
 * @property tokenInfoDao token 資料庫(通過 Hilt 自動注入)
 * @property raspberryMessage 樹莓派訊息
 * @property recognitionMessage 辨識聲音訊息
 * @property sampleMessage 樣本訊息
 * @property connectedToWebSocket WebSocket 連線(通過 Hilt 自動注入)
 *
 * @property notificationManager 通知管理器
 * @property notificationId 通知ID
 * @property notificationChannelId 通知頻道ID
 * @property notificationBuilder 建構通知
 *
 * @author Ashley
 */
@HiltViewModel
class WebSocketViewModel @Inject constructor(
    private val tokenInfoDao: TokenInfoDao,
    private val webSocketManager: WebSocketManager,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _raspberryMessage = MutableLiveData("")
    val raspberryMessage: LiveData<String>
        get() = _raspberryMessage

    private val _recognitionMessage = MutableLiveData("")
    val recognitionMessage: LiveData<String>
        get() = _recognitionMessage

    private val _sampleMessage = MutableLiveData("")
    val sampleMessage: LiveData<String>
        get() = _sampleMessage

    val connectedToWebSocket: LiveData<Boolean>
        get() = webSocketManager.isConnected

//    private val notificationManager =
//        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).apply {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                createNotificationChannel(NotificationChannel(
//                    "voice notification",
//                    "fire",
//                    NotificationManager.IMPORTANCE_HIGH
//                ))
//            }
//        }
//    private val notificationId = 1
//    private val notificationChannelId = BuildConfig.NOTIFICATiON_CHANNEL_ID
//    private val notificationBuilder = NotificationCompat.Builder(
//        context, notificationChannelId
//    )
//        .setSmallIcon(R.drawable.voice)
//        .setContentTitle("voice channel")
//        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    init {
        viewModelScope.launch {
            webSocketManager.messageLiveData.observeForever { message ->
                when {
                    message.isEmpty() || message.isBlank() -> { }
                    message.isReturnNotification() -> {
//                        showNotification(message)
                        // TODO Peng
                        _recognitionMessage.postValue(message)
                    }
                    message.isReturnAddSample() -> {
                        // TODO Peng
                        _sampleMessage.postValue(message)
                    }
                    message.isCannotFoundUuid() -> {
                        _raspberryMessage.postValue(message)
                    }
                    else -> {
                        // TODO Ashley
                        _raspberryMessage.postValue(message)
                    }
                }
            }
        }
    }

    companion object {
        const val CANNOT_FOUND_UUID = "cannot found uuid"
    }

    /**
     * 連線到 WebSocket， [tokenInfoDao] 取 token
     *
     * @author Ashley
     */
    fun connectToWebSocket() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = tokenInfoDao.getToken()?.token ?: return@launch
            webSocketManager.connect(token)
        }
    }


    /**
     * 發送連線請求
     *
     * @param uuid 樹莓派uuid
     *
     * @author Ashley
     */
    fun sendConnectRequest(uuid: String) {
//        if (uuid.length != 12) {
//            throw IllegalArgumentException("uuid length is not 12")
//        }
        sendMessageToBackend("app://connect?uuid=$uuid")
    }

    public override fun onCleared() {
        viewModelScope.launch {
            webSocketManager.close()
        }
        super.onCleared()
    }

    /**
     * 此方法只負責傳送至後端，不負責檢查格式
     *
     * @param message 訊息
     *
     * @author Ashley
     */
    private fun sendMessageToBackend(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            webSocketManager.sendMessage(message)
        }
    }

//    fun notification(name:String = "fire zai"){
//        showNotification(name)
//    }
//    private fun showNotification(message: String){
//        val intent = Intent(context, MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//        notificationBuilder.addAction(R.drawable.voice, "open mic?", pendingIntent)
//        notificationBuilder.setContentText(message)
//        notificationManager.notify(notificationId, notificationBuilder.build())
////        Log.i("Arvin-log", "notification enable is ${NotificationManagerCompat.from(context).areNotificationsEnabled()}")
//    }

}

private fun String.isReturnNotification(): Boolean {
    // TODO Peng
    return true
}

private fun String.isReturnAddSample(): Boolean {
    // TODO Peng
    return true
}

private fun String.isCannotFoundUuid(): Boolean {
    return this == WebSocketViewModel.CANNOT_FOUND_UUID
}

