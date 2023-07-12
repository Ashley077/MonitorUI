package com.example.myapplication.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Routes
import com.example.myapplication.viewmodel.AccountViewModel
import com.example.myapplication.viewmodel.LogOutViewModel
import com.example.myapplication.viewmodel.LoginViewModel


@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController, accountViewModel = hiltViewModel(), loginViewModel = hiltViewModel())
        }
//        viewModel = hiltViewModel()
        composable(Routes.SignUp.route) {
            Register(navController = navController, registerViewModel = hiltViewModel())
        }

        composable(Routes.ForgotPassword.route) {
            ForgotPassword(navController = navController)
        }
        

        composable(Routes.Voice.route){
            Voice(navController = navController, logOutViewModel = hiltViewModel())
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

        composable(Routes.ConnectStatus.route){
            ConnectStatus(navController = navController)

        }
    }
}