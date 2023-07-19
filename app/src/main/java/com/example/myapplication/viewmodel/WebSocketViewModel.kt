package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.BuildConfig
import com.example.myapplication.manager.WebSocketManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebSocketViewModel @Inject constructor(): ViewModel() {
    private val webSocketManager = WebSocketManager()
    val messageState = MutableLiveData("")

    fun connectWebSocket(){
        GlobalScope.launch {
            webSocketManager.connect(BuildConfig.WS_BASE_URL)
        }
    }

    fun sendMessage(message: String){
        GlobalScope.launch {
            webSocketManager.sendMessage(message)
        }
    }

    init {
        GlobalScope.launch {
            webSocketManager.messageLiveData.observeForever { message ->
                messageState.value = message
            }
        }

    }

    public override fun onCleared() {
        webSocketManager.close()
        super.onCleared()
    }
}