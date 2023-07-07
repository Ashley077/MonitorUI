package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.manager.RemoteClientManager
import com.example.myapplication.model.AccountService
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.screen.ScreenMain
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.*

class MainActivity : ComponentActivity() {
    private lateinit var accountViewModel: AccountViewModel
    private lateinit var loginViewModelFactory: LoginViewModelFactory
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var logOutViewModelFactory: LogOutViewModelFactory
    private lateinit var logOutViewModel: LogOutViewModel
    private lateinit var tokenInfoDao: TokenInfoDao
    private val accountService = AccountService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accountFactory = AccountViewModelFactory(accountService)
        tokenInfoDao = (application as MyapplicationApplication).db.tokenInfoDao
        accountViewModel = ViewModelProvider(this, accountFactory)[AccountViewModel::class.java]
        loginViewModelFactory = LoginViewModelFactory(tokenInfoDao)
        loginViewModel = ViewModelProvider(this, loginViewModelFactory)[LoginViewModel::class.java]
        logOutViewModelFactory = LogOutViewModelFactory(tokenInfoDao)
        logOutViewModel = ViewModelProvider(this, logOutViewModelFactory)[LogOutViewModel::class.java]

        setContent {
            MyApplicationTheme {
                /*Surface(
                  modifier = Modifier.fillMaxSize(),
                  color = MaterialTheme.colors.background
              ){*/
                ScreenMain(accountViewModel = accountViewModel, loginViewModel = loginViewModel, logOutViewModel = logOutViewModel)
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