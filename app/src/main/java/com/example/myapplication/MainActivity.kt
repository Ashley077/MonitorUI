package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.model.AccountService
import com.example.myapplication.screen.ScreenMain
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Purple700
import com.example.myapplication.viewmodel.AccountViewModel
import com.example.myapplication.viewmodel.AccountViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var accountViewModel: AccountViewModel
    private val accountService = AccountService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val accountFactory = AccountViewModelFactory(accountService)
        accountViewModel = ViewModelProvider(this, accountFactory)[AccountViewModel::class.java]
        setContent {
            MyApplicationTheme {
                  /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){*/
                   ScreenMain(accountViewModel = accountViewModel)
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