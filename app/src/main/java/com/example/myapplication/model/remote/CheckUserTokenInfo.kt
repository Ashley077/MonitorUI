package com.example.myapplication.model.remote

import com.google.gson.annotations.SerializedName

/**
 * 確認 Token 使用
 *
 * @param token
 *
 * @author Ashley
 */
data class CheckUserTokenInfo(
    @SerializedName("token")
    val token: String
)