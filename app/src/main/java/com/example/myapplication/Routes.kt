package com.example.myapplication

import androidx.compose.foundation.shape.RoundedCornerShape

sealed class Routes(val route: String) {
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object Login : Routes("Login")
    object voice : Routes("voice")
    object voicerecognition : Routes("voicerecognition")
    object samplelist : Routes("samplelist")
    object historicalrecord : Routes("historicalrecord")
    object Dashboard : Routes("Dashboard")
    object Home : Routes("Home")
}