package com.example.myapplication.manager

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.BuildConfig
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @property isConnected 連線狀態
 *
 * @author Ashley
 */
@Singleton
class WebSocketManager @Inject constructor() {
    private lateinit var client: HttpClient
    private lateinit var webSocketSession: DefaultWebSocketSession
    val messageLiveData = MutableLiveData("")

    private val _isConnected = MutableLiveData(false)
    val isConnected: LiveData<Boolean>
        get() = _isConnected

    /**
     * 建立 WebSocket 連線，連線成功更新連線狀態
     *
     * @param token 連線需要的 token 字串，token 放在 header 中
     *
     * @author Ashley
     */
    suspend fun connect(token: String) {
        client = HttpClient {
            install(WebSockets)
        }
        try {
            webSocketSession = client.webSocketSession {
//                this.url.takeFrom(URLBuilder().apply {
//                    takeFrom(URL(erl))
//                })
                this.url.set(
                    URLProtocol.WS.name,
                    BuildConfig.WS_BASE_HOST,
                    BuildConfig.WS_BASE_PORT
                )
                this.headers.append("token", token)
            }
            _isConnected.postValue(true)
            startListening()
        } catch (e: Exception) {
            _isConnected.postValue(false)
            e.printStackTrace()
            Log.i("Ashley-log", e.message ?: "WTF")
        }
    }

    /**
     * webSocket 連線上傳送消息
     *
     * @param message 傳送的訊息
     *
     * @author Ashley
     */
    suspend fun sendMessage(message: String) {
        withContext(Dispatchers.IO) {
            if (_isConnected.value == true) {
                webSocketSession.send(Frame.Text(message))
            }
        }

    }

    suspend fun close() {
        withContext(Dispatchers.IO) {
            if (_isConnected.value == true) {
                webSocketSession.close()
                _isConnected.postValue(false)
            }
            client.close()
        }
    }

    /**
     * WebSocket 連線上監聽接收的消息
     *
     * @author Ashley
     */
    private suspend fun startListening() {
        withContext(Dispatchers.IO) {
            try {
                Log.i("Ashley-log", "Start YA!!!")
                for (frame in webSocketSession.incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val message = frame.readText()
                            messageLiveData.postValue(message)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.i("Ashley-log", "${e.message}")
                _isConnected.postValue(false)
            }
        }
    }

}