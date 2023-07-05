package com.example.myapplication.model.remote

import com.google.gson.annotations.SerializedName


/**
 * 註冊結果
 *
 * @param status 狀態碼
 * @param message 錯誤訊息
 * @param resultMessage 回傳訊息
 *
 * @author Ashley
 */
data class RegisterResult(

    @SerializedName("resultStatus")
    val status: Int,

    @SerializedName("errorMessage")
    val message: String,

    @SerializedName("resultMessage")
    val resultMessage: String
)
