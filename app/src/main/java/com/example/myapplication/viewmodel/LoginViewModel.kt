package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.manager.RemoteClientManager
import com.example.myapplication.model.data.local.dao.UserInfoDao
import com.example.myapplication.model.remote.UserInfo
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
class LoginViewModel (private val userInfoDao: UserInfoDao) : ViewModel(){
    val allUser = userInfoDao.getAllUser()

    private val _status = MutableLiveData<RemoteLoginStatus>(RemoteLoginStatus.Init)

    /**
     * 初始化為 Init 發送請求 loading 登入成功 Success 登入失敗 Failed
     *
     * @author Ashley
     */
    val status: LiveData<RemoteLoginStatus>
        get() = _status

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
            val result = RemoteClientManager.apiServiceClient.login(UserInfo(account, password))
            if (result.status == HttpURLConnection.HTTP_OK) {
                _status.postValue(RemoteLoginStatus.Success(account))
            } else {
                _status.postValue(RemoteLoginStatus.Failed(Throwable(result.message)))
            }
        }
    }

    fun addUser(name : String , password : String){
        viewModelScope.launch(Dispatchers.IO){
//            userInfoDao.insert(UserInfo(0 ,name ,password))
        }
    }

    fun deleteUser(userInfo: UserInfo){
        viewModelScope.launch(Dispatchers.IO) {
//            userInfoDao.delete(userInfo)
        }
    }

}

/**
 * 此類別提供狀態
 */
sealed class RemoteLoginStatus {
    /**
     * 初始化
     */
    object Init: RemoteLoginStatus()

    /**
     * 發送請求到取得結果之間的狀態
     */
    object Loading: RemoteLoginStatus()

    /**
     * 結果成功狀態
     */
    class Success(val account: String): RemoteLoginStatus()

    /**
     * 結果失敗狀態
     */
    class Failed(val throwable: Throwable): RemoteLoginStatus()
}
