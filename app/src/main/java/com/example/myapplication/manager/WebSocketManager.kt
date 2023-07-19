package com.example.myapplication.manager

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

    suspend fun connect(erl: String){
        client = HttpClient{
            install(WebSockets)
        }
        webSocketSession = client.webSocketSession {
            this.url.takeFrom(URLBuilder().apply {
                takeFrom(URL(BuildConfig.WS_BASE_URL))
            })
        }

        startListening()
    }
    fun sendMessage(message: String){
        GlobalScope.launch(Dispatchers.IO){
            webSocketSession.send(Frame.Text(message))
        }
    }
    fun close(){
        GlobalScope.launch(Dispatchers.IO){
            webSocketSession.close()
            client.close()
        }
    }

    private fun startListening(){
        GlobalScope.launch(Dispatchers.IO){
            try{
                for(frame in webSocketSession.incoming){
                    when(frame){
                        is Frame.Text -> {
                            val message = frame.readText()
                            messageLiveData.value = message
                        }
                    }
                }
            } catch (e: Exception){

            }
        }
    }
}