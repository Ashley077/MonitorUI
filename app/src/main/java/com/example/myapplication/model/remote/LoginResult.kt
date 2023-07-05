package com.example.myapplication.model.remote

import com.google.gson.annotations.SerializedName

/**
 * 登入結果
 *
 * @param status 狀態碼
 * @param message 錯誤訊息
 * @param data token
 *
 * @author Ashley
 */
data class LoginResult(
    @SerializedName("resultStatus")
    val status: Int,

    @SerializedName("errorMessage")
    val message: String,

    @SerializedName("resultData")
    val data: String
)
