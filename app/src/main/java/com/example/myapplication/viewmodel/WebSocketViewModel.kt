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
 * @property raspberryMessage 樹莓派訊息
 * @property recognitionMessage 辨識聲音訊息
 * @property sampleMessage 樣本訊息
 * @property connectedToWebSocket WebSocket 連線(通過 Hilt 自動注入)
 *
 * @author Ashley
 */
@HiltViewModel
class WebSocketViewModel @Inject constructor(
    private val tokenInfoDao: TokenInfoDao,
    private val webSocketManager: WebSocketManager
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


    init {
        viewModelScope.launch {
            webSocketManager.messageLiveData.observeForever { message ->
                when {
                    message.isEmpty() || message.isBlank() -> { }
                    message.isReturnNotification() -> {
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