package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.model.Notification
import com.example.myapplication.model.WebSocketService
import com.example.myapplication.screen.ScreenMain
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dagger.hilt.android.AndroidEntryPoint


const val POST_NOTIFICATIONS = "android.permission.POST_NOTIFICATIONS"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.i(
                "Ashley-log",
                "notification enable is ${
                    NotificationManagerCompat.from(this).areNotificationsEnabled()
                }"
            )
            MyApplicationTheme {

                RequestPermissionUsingAccompanist()
            }
        }
    }


    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    private fun RequestPermissionUsingAccompanist() {
        val permissions = mutableListOf<String>(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            permissions.add(Manifest.permission.FOREGROUND_SERVICE)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(POST_NOTIFICATIONS)
        }
        val permissionState = rememberMultiplePermissionsState(permissions = permissions)
        PermissionsRequired(
            multiplePermissionsState = permissionState,
            permissionsNotGrantedContent = {
                Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = { permissionState.launchMultiplePermissionRequest() }) {
                        Text(text = "請求權限")

                    }
                }

            },
            permissionsNotAvailableContent = {
                Button(onClick = { permissionState.launchMultiplePermissionRequest() }) {
                    Text(text = "需要權限")
                }
            },
            content = {
                ScreenMain()
                LaunchedEffect(Unit) {
                    val serviceIntent = Intent(this@MainActivity, WebSocketService::class.java)
                    ContextCompat.startForegroundService(this@MainActivity,serviceIntent)
                }
            }
        )
    }

    @Composable
    fun notifytest(name:String){
        Button(onClick = {
            Notification(this, "居家監控", "火災").firNotification()
        }) {
            Text(text = "notification")
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