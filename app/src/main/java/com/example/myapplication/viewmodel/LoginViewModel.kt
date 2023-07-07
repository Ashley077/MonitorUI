package com.example.myapplication.viewmodel

import android.util.Log
import  androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.manager.RemoteClientManager
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.model.data.local.entity.TokenInfo
import com.example.myapplication.model.remote.LoginResult
import com.example.myapplication.model.remote.UserInfo
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

/**
 * 此類別提供登入服務
 *
 * @property status 狀態碼
 *
 * @author Ashley
 */
class LoginViewModel(private val tokenInfoDao: TokenInfoDao) : ViewModel() {

    private val _status = MutableLiveData<RemoteLoginStatus>(RemoteLoginStatus.Init)

    /**
     * 初始化為 Init 發送請求 loading 登入成功 Success 登入失敗 Failed
     *
     * @author Ashley
     */
    val status: LiveData<RemoteLoginStatus>
        get() = _status

    /**
     * 此方法提供搜尋 Token 的服務
     *
     * 確認 Token 能不能使用 ， 如果能使用則登入成功
     *
     * @author Ashley
     */
    fun searchToken() {
        _status.value = RemoteLoginStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = tokenInfoDao.getToken()
            if (result == null || result.token.isEmpty()) {
                _status.postValue(RemoteLoginStatus.Init)
            } else {
                // send to backend check this token can be used
                // Yes
                _status.postValue(RemoteLoginStatus.Success(""))
                // No
//                _status.postValue(RemoteLoginStatus.Init)
            }
        }
    }

    /**
     * login 提供到後端的請求 此方法會改變 [status] 的狀態
     *
     * @param account 使用者帳號
     * @param password 使用者密碼
     *
     * @author Ashley
     */
    fun login(account: String, password: String) {
        _status.value = RemoteLoginStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    RemoteClientManager.apiServiceClient.login(UserInfo(account, password))
//                if (result.status == HttpURLConnection.HTTP_OK) {
//                    _status.postValue(RemoteLoginStatus.Success(account))
//                }
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    _status.postValue(RemoteLoginStatus.Success(account))
                    response.body()?.data?.let {
                        tokenInfoDao.insert(TokenInfo(1, it))
                    }
                    Log.i("Ashley-log", response.body()?.data ?: "no token")
                } else if (response.code() == HttpURLConnection.HTTP_BAD_REQUEST) {
                    if (response.errorBody() != null) {
                        response.errorBody()?.let {
                            val gson = GsonBuilder().create()
                            val loginResult = gson.fromJson(it.string(), LoginResult::class.java)
//                    Log.i("Ashley-log", loginResult.toString())
                            var errStr = ""
                            when(loginResult.message) {
                                "account or password is wrong" -> {
                                    errStr = "帳號密碼錯誤"
                                }
                                else -> {
                                    errStr = "請稍後再試"
                                }
                            }
                            _status.postValue(
                                RemoteLoginStatus.Failed(
                                    Throwable(
                                        errStr
                                    )
                                )
                            )
                        }
                    } else {
                        _status.postValue(
                            RemoteLoginStatus.Failed(
                                Throwable(
                                    "請稍後再試"
                                )
                            )
                        )
                    }
                }
            }
            catch (e: Exception) {
                _status.postValue(
                    RemoteLoginStatus.Failed(
                        Throwable(
                            "請稍後再試"
                        )
                    )
                )
            }
        }
    }


    fun resetStatus() {
        _status.value = RemoteLoginStatus.Init

    }
}

/**
 * 此類別提供狀態
 */
sealed class RemoteLoginStatus {
    /**
     * 初始化
     */
    object Init : RemoteLoginStatus()

    /**
     * 發送請求到取得結果之間的狀態
     */
    object Loading : RemoteLoginStatus()

    /**
     * 結果成功狀態
     */
    class Success(val account: String) : RemoteLoginStatus()

    /**
     * 結果失敗狀態
     */
    class Failed(val throwable: Throwable) : RemoteLoginStatus()
}
