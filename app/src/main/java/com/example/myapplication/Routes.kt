package com.example.myapplication

import androidx.compose.foundation.shape.RoundedCornerShape

sealed class Routes(val route: String) {
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object Login : Routes("Login")
    object Voice : Routes("Voice")
    object VoiceRecognition : Routes("VoiceRecognition")
    object SampleList : Routes("SampleList")
    object HistoricalRecord : Routes("HistoricalRecord")
    object Loading : Routes("Loading")


}