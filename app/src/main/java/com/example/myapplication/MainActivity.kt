package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.manager.RemoteClientManager
import com.example.myapplication.model.AccountService
import com.example.myapplication.model.WebSocketService
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.screen.ScreenMain
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                LaunchedEffect(Unit) {
                    val serviceIntent = Intent(this@MainActivity, WebSocketService::class.java)
                    startService(serviceIntent)
                }
                ScreenMain()
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