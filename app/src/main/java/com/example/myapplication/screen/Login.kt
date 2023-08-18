package com.example.myapplication.screen

//import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.Routes
import com.example.myapplication.component.autoCloseKeyboard
import com.example.myapplication.ui.theme.Purple700
import com.example.myapplication.viewmodel.AccountViewModel
import com.example.myapplication.viewmodel.LoginViewModel
import com.example.myapplication.viewmodel.RemoteLoginStatus

@Composable
fun LoginPage(
    navController: NavHostController,
    accountViewModel: AccountViewModel,
    loginViewModel: LoginViewModel
) {

    val firstSearch = remember{ mutableStateOf(true) }

    val inputUserName = accountViewModel.inputUserNameData.observeAsState()
    val inputPassword = accountViewModel.inputPasswordData.observeAsState()
    val canButtonEnable = accountViewModel.canLoginButtonEnableData.observeAsState()

    val status = loginViewModel.status.observeAsState()
    when (status.value) {
        RemoteLoginStatus.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Red
                )
            }
        }
        is RemoteLoginStatus.Success -> {
            if (navController.currentBackStackEntry?.destination?.route == Routes.Login.route) {
                loginViewModel.resetStatus()
                accountViewModel.setInputPassword("")
                navController.navigate(Routes.Voice.route)
            }
        }
        else -> {
            if (status.value is RemoteLoginStatus.Init && firstSearch.value) {
                firstSearch.value = false
                loginViewModel.searchToken()
            }
            Box(modifier = Modifier.fillMaxSize().autoCloseKeyboard()) {
                ClickableText(
                    text = AnnotatedString("Sign up here"),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    onClick = { navController.navigate(Routes.SignUp.route) },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default,
                        textDecoration = TextDecoration.Underline,
                        color = Purple700
                    )
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "Login",
                    style = TextStyle(fontSize = 50.sp, fontFamily = FontFamily.Cursive)
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Username") },
                    value = inputUserName.value ?: "",
                    onValueChange = { accountViewModel.setInputUserName(it) }
                )

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Password") },
                    value = inputPassword.value ?: "",
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { accountViewModel.setInputPassword(it) })

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),

                        onClick = {
                            loginViewModel.login(
                                inputUserName.value ?: "",
                                inputPassword.value ?: ""
                            )

//                        coroutineScope.launch(Dispatchers.Main) {
//                            val isSuccess: Boolean = accountViewModel.login()
//                            if (isSuccess) {
//                                navController.navigate(Routes.Voice.route)
//                            } else {
//
//                            }
//                        }
//                        loginViewModel.addUser(inputUserName.value ?: "", inputPassword.value ?: "")
                        },
                        enabled = canButtonEnable.value ?: false,
                        shape = RoundedCornerShape(50.dp),

                        ) {
                        Text(text = "Login")
                    }
                }
                if (status.value is RemoteLoginStatus.Failed) {
                    Box(modifier = Modifier.padding(40.dp, 10.dp, 40.dp, 0.dp)) {
                        Text(
                            text = (status.value as RemoteLoginStatus.Failed).throwable.message ?: "請稍後再試?",
                            fontSize = 25.sp,
                            color = Color.Red
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ClickableText(
                    text = AnnotatedString("Forgot password?"),
                    onClick = { navController.navigate(Routes.ForgotPassword.route) },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default
                    )
                )
            }
        }
    }
}
