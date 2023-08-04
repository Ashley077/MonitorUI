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
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor(){
    private lateinit var client: HttpClient
    private lateinit var webSocketSession: DefaultWebSocketSession
    val messageLiveData = MutableLiveData("")

    private val _isConnected = MutableLiveData(false)
    val isConnected: LiveData<Boolean>
        get() = _isConnected

    suspend fun connect(erl: String, token: String){
        client = HttpClient{
            install(WebSockets)
        }
        try {
            webSocketSession = client.webSocketSession {
//                this.url.takeFrom(URLBuilder().apply {
//                    takeFrom(URL(erl))
//                })
                this.url.set(URLProtocol.WS.name, "120.110.113.211", 8080)
                this.headers.append("token", token)
            }
            _isConnected.postValue(true)
            startListening()
        } catch (e: Exception){
            _isConnected.postValue(false)
            e.printStackTrace()
            Log.i("Ashley-log", e.message ?: "WTF")
        }
    }
    fun sendMessage(message: String){
        GlobalScope.launch(Dispatchers.IO){
            if (_isConnected.value == true){
                webSocketSession.send(Frame.Text(message))
            }
        }
    }
    fun close(){
        GlobalScope.launch(Dispatchers.IO){
            if (_isConnected.value == true){
                webSocketSession.close()
                _isConnected.postValue(false)
            }
            client.close()
        }
    }

    private fun startListening(){
        GlobalScope.launch(Dispatchers.IO){
            try{
                Log.i("Ashley-log", "Start YA!!! ")
                for(frame in webSocketSession.incoming){
                    when(frame){
                        is Frame.Text -> {
                            val message = frame.readText()
                            messageLiveData.postValue(message)
                        }
                    }
                }
            } catch (e: Exception){
                Log.i("Ashley-log", "${e.message}")
                _isConnected.postValue(false)
            }
        }
    }


}