package com.example.myapplication.model

import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountService @Inject constructor() {
    companion object {
        const val FAKE_USER_NAME = "monitor"
        const val FAKE_PASSWORD = "0525"
    }

    suspend fun login(userName: String, password: String): Boolean {
        val second = (2..4).shuffled()[0]
        delay(second * 1000L)
        return userName == FAKE_USER_NAME && password == FAKE_PASSWORD
    }
}