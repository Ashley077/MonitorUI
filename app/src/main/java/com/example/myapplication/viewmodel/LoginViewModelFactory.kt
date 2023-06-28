package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.model.data.local.dao.UserInfoDao

class LoginViewModelFactory(private val userInfoDao: UserInfoDao): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userInfoDao) as T
        }
        throw IllegalArgumentException("unknown viewModel class type")
    }
}