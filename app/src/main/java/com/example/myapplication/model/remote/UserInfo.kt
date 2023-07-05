package com.example.myapplication.model.remote

import com.example.myapplication.BuildConfig
import com.google.gson.annotations.SerializedName

/**
 * 登入使用，配合 RemoteClientManager 做使用
 *
 * @param account 使用者帳號
 * @param password 使用者密碼
 *
 * @author Ashley
 */
data class UserInfo(
    @SerializedName("account")
    val account: String,

    @SerializedName("pwd")
    val password: String
) {
    fun a() {
        BuildConfig.BASE_URL
    }
}
