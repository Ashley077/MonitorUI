package com.example.myapplication.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Routes

@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }

        composable(Routes.SignUp.route) {
            Signup(navController = navController)
        }

        composable(Routes.ForgotPassword.route) {
            ForgotPassword(navController = navController)
        }
        

        composable(Routes.Voice.route){
            Voice(navController = navController)
        }

        composable(Routes.VoiceRecognition.route){
            VoiceRecognition(navController = navController)
        }
        
        composable(Routes.SampleList.route){
            SampleList(navController = navController)
        }

        composable(Routes.HistoricalRecord.route){
            HistoricalRecord(navController = navController)
        }

        composable(Routes.Loading.route){
            Loading(navController = navController)

        }
    }
}