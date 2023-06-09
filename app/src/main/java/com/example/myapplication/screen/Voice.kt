package com.example.myapplication.screen

import androidx.annotation.DisplayContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.Purple700
import com.example.myapplication.R
import com.example.myapplication.Routes
import com.example.myapplication.component.CustomTopAppBar
import com.example.myapplication.viewmodel.LogOutViewModel
import kotlin.math.log

@Composable

fun Voice(navController: NavHostController, logOutViewModel: LogOutViewModel){

    Box(modifier = Modifier.fillMaxSize()){
        DisplayImage(navController, logOutViewModel)
    }
}

@Composable
fun DisplayImage(navController: NavHostController, logOutViewModel: LogOutViewModel){
    Scaffold (
        topBar = {
            CustomTopAppBar(navController, "voice", true)
        }, content = { it
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                ,verticalArrangement = Arrangement.Center
                ,horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "voice", style = TextStyle(fontSize = 70.sp), fontFamily = FontFamily.Cursive)

                Spacer(modifier = Modifier.height(40.dp))
                MyImage()
                Spacer(modifier = Modifier.height(40.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(

                        onClick = { navController.navigate(Routes.VoiceRecognition.route)},


                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Add Voice")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(

                        onClick = { navController.navigate(Routes.SampleList.route)},


                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Sample List")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(

                        onClick = { navController.navigate(Routes.HistoricalRecord.route)},

                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Historical Record")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 20.dp)) {
                    Button(

                        onClick = { navController.navigate(Routes.ConnectStatus.route)},

                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Connect Status")
                    }
                }
                Spacer(modifier = Modifier.height(125.dp))


            }
            Box(modifier = Modifier.fillMaxSize()) {
                ClickableText(
                    text = AnnotatedString("log out"),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(10.dp),
                    onClick = {
                        logOutViewModel.logout()
                        navController.navigate(Routes.Login.route)
                    },
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Cursive,
                        textDecoration = TextDecoration.Underline,
                        color = Purple700
                    )
                )
            }
        }
    )
}

@Composable
fun MyImage(){
    Image(painter = painterResource(id = R.drawable.voice),
        contentDescription = "Image",
        modifier = Modifier
            .padding(10.dp)
            .width(200.dp)
            .height(200.dp)
    )
}

@Composable
@Preview
fun displayVoice() {
    DisplayImage(navController = rememberNavController(), logOutViewModel = hiltViewModel())
}