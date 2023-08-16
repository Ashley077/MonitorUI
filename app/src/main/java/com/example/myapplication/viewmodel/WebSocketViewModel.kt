package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.manager.WebSocketManager
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * WebSocketViewModel 連線功能
 *
 * @property tokenInfoDao token 資料庫(通過 Hilt 自動注入)
 * @property messageState 訊息狀態
 * @property connectedToWebSocket WebSocket 連線
 *
 * @author Ashley
 */
@HiltViewModel
class WebSocketViewModel @Inject constructor(private val tokenInfoDao: TokenInfoDao) : ViewModel() {
    private val webSocketManager = WebSocketManager()
    private val _messageState = MutableLiveData("")
    val messageState: LiveData<String>
        get() = _messageState

    val connectedToWebSocket: LiveData<Boolean>
        get() = webSocketManager.isConnected

    init {
        viewModelScope.launch {
            webSocketManager.messageLiveData.observeForever { message ->
                if (message.isEmpty() || message.isBlank()) {
                    Log.i("Ashley-log", "is empty")
                }
                _messageState.postValue(message)
            }
        }
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
     * @pram message 訊息
     *
     * @author Ashley
     */
    private fun sendMessageToBackend(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            webSocketManager.sendMessage(message)
        }
    }
}

