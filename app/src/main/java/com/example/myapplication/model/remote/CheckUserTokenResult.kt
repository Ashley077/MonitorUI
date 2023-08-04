package com.example.myapplication.model.remote

import com.google.gson.annotations.SerializedName

data class CheckUserTokenResult(
    @SerializedName("msg")
    val tokenStatusMessage: String
)
