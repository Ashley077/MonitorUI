package com.example.myapplication.model.remote

import com.google.gson.annotations.SerializedName

data class CheckUserTokenInfo(
    @SerializedName("token")
    val token: String
)