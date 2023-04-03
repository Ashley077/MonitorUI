package com.example.myapplication.screen

import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Routes
import com.example.myapplication.component.CustomTopAppBar
import com.example.myapplication.R

@Composable

fun VoiceRecognition(navController: NavController){

    Box(modifier = Modifier.fillMaxSize()){
        scaffoldvoice(navController)
    }
}

@Composable
fun scaffoldvoice(navController: NavController){
    Scaffold(
        topBar =  {
            CustomTopAppBar(navController ,"voice recognition",true)
        }, content = {
            Column( modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Voice Recognition",style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily.Cursive))
                Spacer(modifier = Modifier.height(20.dp))
                
                voicetest()
                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "identify")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Stop")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(

                        onClick = {navController.navigate(Routes.Voice.route)},

                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    )
}

@Composable
fun voicetest(){
    Image(painter = painterResource(id = R.drawable.voicetest)
        , contentDescription = "Image"
        ,modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(200.dp))
    
}