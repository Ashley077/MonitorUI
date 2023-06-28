package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.local.dao.UserInfoDao
import com.example.myapplication.model.data.local.entity.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel (private val userInfoDao: UserInfoDao) : ViewModel(){
    val allUser = userInfoDao.getAllUser()

    fun addUser(name : String , password : String){
        viewModelScope.launch(Dispatchers.IO){
            userInfoDao.insert(UserInfo(0 ,name ,password))
        }
    }

    fun deleteUser(userInfo: UserInfo){
        viewModelScope.launch(Dispatchers.IO) {
            userInfoDao.delete(userInfo)
        }
    }

}