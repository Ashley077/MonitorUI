package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Routes
import com.example.myapplication.component.CustomTopAppBar


@Composable
fun Signup(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        scaffoldSignup(navController)
    }
}

@Composable
fun scaffoldSignup(navController: NavController){
    Scaffold(
        topBar = {
            CustomTopAppBar(navController ,"Signup",true)
        }, content = { it
            Column(modifier = Modifier.fillMaxSize(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val email = remember { mutableStateOf(TextFieldValue()) }

                Text(text = "Sign Up", style = TextStyle(fontSize = 50.sp,fontFamily = FontFamily.Cursive))
                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it })
                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    label = { Text(text = "Email") },
                    value =email.value,
                    onValueChange = { email.value = it })
                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {navController.navigate(Routes.Login.route)},
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Create Account")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}