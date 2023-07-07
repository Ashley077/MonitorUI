package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.model.data.local.entity.TokenInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  登出後 ， 刪除 Token
 *
 *  @author Ashley
 */
class LogOutViewModel(private val tokenInfoDao: TokenInfoDao): ViewModel() {
    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            tokenInfoDao.delete(TokenInfo(1, ""))
        }
    }
}