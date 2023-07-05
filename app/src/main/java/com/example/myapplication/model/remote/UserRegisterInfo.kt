package com.example.myapplication.model.remote

import com.google.gson.annotations.SerializedName


/**
 * 註冊使用
 *
 * @param account 使用者帳號
 * @param password 使用者密碼
 * @param email 使用者信箱
 *
 * @author Ashley
 */
data class UserRegisterInfo(
    @SerializedName("account")
    val account: String,

    @SerializedName("pwd")
    val password: String,

    @SerializedName("email")
    val email: String
)