package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.model.data.local.entity.TokenInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  登出後 ， 刪除 Token
 *
 *  @author Ashley
 */
@HiltViewModel
class LogOutViewModel @Inject constructor(private val tokenInfoDao: TokenInfoDao): ViewModel() {
    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            tokenInfoDao.delete(TokenInfo(1, ""))
        }
    }
}