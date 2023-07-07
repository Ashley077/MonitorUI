package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.model.data.local.dao.TokenInfoDao

class LoginViewModelFactory(private val tokenInfoDao: TokenInfoDao): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(tokenInfoDao) as T
        }
        throw IllegalArgumentException("unknown viewModel class type")
    }
}