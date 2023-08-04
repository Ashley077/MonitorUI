package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.BuildConfig
import com.example.myapplication.manager.WebSocketManager
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebSocketViewModel @Inject constructor(private val tokenInfoDao: TokenInfoDao): ViewModel() {
    private val webSocketManager = WebSocketManager()
    private val _messageState = MutableLiveData("")
    val messageState : LiveData<String>
        get() = _messageState
    var uuid by mutableStateOf("")

    val connectedToWebSocket: LiveData<Boolean>
        get() = webSocketManager.isConnected

    fun connectToWebSocket(){
        viewModelScope.launch(Dispatchers.IO) {
            val token = tokenInfoDao.getToken()?.token ?: return@launch
            webSocketManager.connect(token)
        }
    }

    fun sendConnectRequest(){
        val formattedMessage = "app://connect?uuid=1"
        viewModelScope.launch(Dispatchers.IO) {
            webSocketManager.sendMessage(formattedMessage)
        }
    }

    init {
        viewModelScope.launch {
            webSocketManager.messageLiveData.observeForever { message ->
                _messageState.postValue(message)
            }
        }

    }

    public override fun onCleared() {
        viewModelScope.launch {
            webSocketManager.close()
        }
        super.onCleared()
    }
}