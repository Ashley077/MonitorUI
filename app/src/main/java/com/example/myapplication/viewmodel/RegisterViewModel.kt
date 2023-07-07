package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.manager.RemoteClientManager
import com.example.myapplication.model.remote.RegisterResult
import com.example.myapplication.model.remote.UserRegisterInfo
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Url
import java.net.HttpURLConnection
import java.net.URLConnection

/**
 * 此類別提供註冊服務
 *
 * @property resultStatus 狀態碼
 *
 * @author Ashley
 */

class RegisterViewModel : ViewModel() {

    private val _resultStatus = MutableLiveData<RemoteRegisterStatus>(RemoteRegisterStatus.Init)

    /**
     * [RemoteRegisterStatus.Init] 初始化
     *
     * [RemoteRegisterStatus.Loading] 傳送請求
     *
     * [RemoteRegisterStatus.Success] 註冊成功
     *
     * [RemoteRegisterStatus.Failed] 註冊失敗
     *
     * @author Ashley
     */
    val resultStatus: LiveData<RemoteRegisterStatus>
        get() = _resultStatus

    /**
     *  register 傳送請求到後端 此方法改變 [resultStatus] 狀態
     *
     *  @param account 使用者帳號
     *  @param password 使用者密碼
     *  @param email 使用者信箱
     *
     *  @author Ashley
     */

    fun register(account: String, password: String, email: String) {
        _resultStatus.value = RemoteRegisterStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RemoteClientManager.apiServiceClient.register(
                    UserRegisterInfo(
                        account,
                        password,
                        email
                    )
                )
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    _resultStatus.postValue(RemoteRegisterStatus.Success)
                } else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                    if (response.errorBody() != null) {
                        response.errorBody()?.let {
                            val gson = GsonBuilder().create()
                            val registerResult = gson.fromJson(it.string(), RegisterResult::class.java)
//                    Log.i("Ashley-log", loginResult.toString())
                            _resultStatus.postValue(
                                RemoteRegisterStatus.Failed(
                                    Throwable(
                                        registerResult.message
                                    )
                                )
                            )
                        }
                    } else {
                        _resultStatus.postValue(
                            RemoteRegisterStatus.Failed(
                                Throwable(
                                    "請稍後再試"
                                )
                            )
                        )
                    }
                }
            }
            catch (e: Exception) {
                _resultStatus.postValue(
                    RemoteRegisterStatus.Failed(
                        Throwable(
                            "請稍後再試"
                        )
                    )
                )
            }
        }
    }
}

/**
 * 此類別提供狀態
 */
sealed class RemoteRegisterStatus {

    /**
     *  Init 初始化
     */
    object Init : RemoteRegisterStatus()

    /**
     * 發送請求到取得結果間的狀態
     */
    object Loading : RemoteRegisterStatus()

    /**
     * 取得成功
     */
    object Success : RemoteRegisterStatus()

    /**
     * 取得失敗
     */
    class Failed(val throwable: Throwable) : RemoteRegisterStatus()
}