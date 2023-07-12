package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Routes
import com.example.myapplication.component.CustomTopAppBar
import com.example.myapplication.viewmodel.RegisterViewModel
import com.example.myapplication.viewmodel.RemoteRegisterStatus


@Composable
fun Register(navController: NavController, registerViewModel: RegisterViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldSignup(navController, registerViewModel = registerViewModel)
    }
}

@Composable
fun ScaffoldSignup(
    navController: NavController,
    registerViewModel: RegisterViewModel
) {
    val resultStatus = registerViewModel.resultStatus.observeAsState()
    when (resultStatus.value) {
        RemoteRegisterStatus.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Red
                )
            }
        }
        RemoteRegisterStatus.Success -> {
            if (navController.currentBackStackEntry?.destination?.route == Routes.SignUp.route) {
                navController.navigate(Routes.Login.route)
            }
        }

        else -> {
            Scaffold(
                topBar = {
                    CustomTopAppBar(navController, "Signup", true)
                }, content = { it1 ->
                    it1
                    if (resultStatus.value is RemoteRegisterStatus.Failed) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(0.dp, 0.dp, 0.dp, 120.dp)
                            ) {
                                Text(
                                    text = (resultStatus.value as RemoteRegisterStatus.Failed).throwable.message
                                        ?: "請稍後再試?",
                                    fontSize = 25.sp,
                                    color = Color.Red,
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val username = remember { mutableStateOf(TextFieldValue()) }
                        val password = remember { mutableStateOf(TextFieldValue()) }
                        val email = remember { mutableStateOf(TextFieldValue()) }


                        Text(
                            text = "Sign Up",
                            style = TextStyle(fontSize = 50.sp, fontFamily = FontFamily.Cursive)
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            label = { Text(text = "Username") },
                            value = username.value,
                            onValueChange = { username.value = it })
                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            label = { Text(text = "Email") },
                            value = email.value,
                            onValueChange = { email.value = it })
                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            label = { Text(text = "Password") },
                            value = password.value,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            onValueChange = { password.value = it })

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                registerViewModel.register(
                                    username.value.text,
                                    password.value.text,
                                    email.value.text,
                                )
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp, 0.dp)
                                .height(50.dp),
                            enabled = canRegister(
                                username.value.text,
                                password.value.text,
                                email.value.text
                            )
                        ) {
                            Text(text = "Create Account")
                        }


                    }
                }
            )
        }
    }

}

fun (String).isUsername(): Boolean {
    return this.length in 4..9
}

fun (String).isPassword(): Boolean {
    return this.length in 4..9
}

fun (String).isEmail(): Boolean {
    if (this.isEmpty()) {
        return false
    }
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun canRegister(username: String, password: String, email: String): Boolean =
    username.isUsername() && password.isPassword() && email.isEmail()