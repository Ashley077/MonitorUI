package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.manager.RemoteClientManager
import com.example.myapplication.model.AccountService
import com.example.myapplication.model.data.local.dao.UserInfoDao
import com.example.myapplication.screen.ScreenMain
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.AccountViewModel
import com.example.myapplication.viewmodel.AccountViewModelFactory
import com.example.myapplication.viewmodel.LoginViewModel
import com.example.myapplication.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var accountViewModel: AccountViewModel
    private lateinit var loginViewModelFactory: LoginViewModelFactory
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userInfoDao: UserInfoDao
    private val accountService = AccountService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accountFactory = AccountViewModelFactory(accountService)
        accountViewModel = ViewModelProvider(this, accountFactory)[AccountViewModel::class.java]
        userInfoDao = (application as MyapplicationApplication).db.userInfoDao
        loginViewModelFactory = LoginViewModelFactory(userInfoDao)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]

        setContent {
            MyApplicationTheme {
                /*Surface(
                  modifier = Modifier.fillMaxSize(),
                  color = MaterialTheme.colors.background
              ){*/
                ScreenMain(accountViewModel = accountViewModel, loginViewModel = loginViewModel)
                RemoteClientManager.apiServiceClient

            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 390, heightDp = 800)

//@Preview(showBackground = true)
/*@Composable
fun DefaultPreview() {
    MyApplicationTheme {
      // DisplayImage()
        ScreenMain(accountViewModel = accountViewModel)
    }
}*/