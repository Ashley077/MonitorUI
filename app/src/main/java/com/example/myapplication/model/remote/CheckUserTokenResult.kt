package com.example.myapplication.model.remote

import com.google.gson.annotations.SerializedName

/**
 * 確認使用者 Token 結果
 *
 * @param tokenStatusMessage Token狀態訊息
 *
 * @author Ashley
 */
data class CheckUserTokenResult(
    @SerializedName("msg")
    val tokenStatusMessage: String
)
