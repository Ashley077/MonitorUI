package com.example.myapplication.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.component.CustomTopAppBar
import com.example.myapplication.manager.WebSocketManager
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.viewmodel.WebSocketViewModel

@Composable
fun ConnectStatus(navController: NavController, webSocketViewModel: WebSocketViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldConnect(navController, webSocketViewModel = webSocketViewModel)
    }
}

@Composable
fun ScaffoldConnect(navController: NavController, webSocketViewModel: WebSocketViewModel) {
    val messageState by webSocketViewModel.messageState.observeAsState(initial = "")
    val connectedToWebSocket by webSocketViewModel.connectedToWebSocket.observeAsState(false)
    val uuid = remember { mutableStateOf("") }
    val uuid1 = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Connect Status", true)
        }, content = {
            it
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Connect Status", modifier = Modifier.align(Alignment.TopCenter),
                        style = TextStyle(
                            fontSize = 50.sp,
                            fontFamily = FontFamily.Cursive,
                        )
                    )
                }
                Box (
                    modifier = Modifier.padding(20.dp),
                    contentAlignment = Alignment.Center
                        ){
                    TextField(
                        value = uuid.value,
                        onValueChange = { input ->
                            uuid.value = input
                        },
//                        modifier = Modifier.weight(0.8f),
                        placeholder = { Text(text = "Enter") },
                        textStyle = TextStyle(
                            fontSize = 24.sp
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.None,
                            imeAction = ImeAction.Done
                        ),
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                BasicTextField(
                    value = uuid1.value,
                    onValueChange = { newValue: String -> uuid1.value = newValue },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.None,
                        imeAction = ImeAction.Done
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (connectedToWebSocket) {
                            webSocketViewModel.sendConnectRequest(uuid = uuid.value)
                            Log.i("Ashley-log", "send")
                        } else {
                            webSocketViewModel.connectToWebSocket()
                            Log.i("Ashley-log", "connect")
                        }
                    },
                    enabled = uuid.value.isNotEmpty()
                ) {
                    Text(
                        text = if (connectedToWebSocket) "Send Connect Request" else "Connect to Raspberry"
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "WebSocket Connection Status: ${if (connectedToWebSocket) "Connected" else "Disconnected"}",
                    style = TextStyle(fontSize = 18.sp),
                )
                Text(
                    text = "樹莓派ID: $messageState",
                    style = TextStyle(fontSize = 18.sp),
                )
            }
        }
    )
}

@Composable
fun DisplayConnectList(webSocketManager: WebSocketManager) {
// this variable use to handle list state
    val notesList = remember {
        mutableStateListOf<String>()
    }
// this variable use to handle edit text input value
    val inputvalue = remember { mutableStateOf(TextFieldValue()) }
    val connectToWebSocket by remember { mutableStateOf(false) }
    var inputMessage by remember { mutableStateOf("") }
    val respberryId by remember { mutableStateOf("") }

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(Dp(60f))
        ) {

            TextField(
                value = inputMessage,
                onValueChange = { inputMessage = it },
//                value = inputvalue.value,
//                onValueChange = {
//                    inputvalue.value = it
//                }
//                modifier = Modifier.weight(0.8f),
//                placeholder = { Text(text = "Enter") },
//                keyboardOptions = KeyboardOptions(
//                    capitalization = KeyboardCapitalization.None,
//                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
//                ),
//                textStyle = TextStyle(
//                    color = Color.Black, fontSize = TextUnit.Unspecified,
//                    fontFamily = FontFamily.SansSerif
//                ),
//                maxLines = 1,
//                singleLine = true
            )


//            Button(
//                onClick = {
//                    notesList.add(inputvalue.value.text)
//                    inputvalue.value = TextFieldValue("")
//                },
//                modifier = Modifier
//                    .weight(0.2f)
//                    .fillMaxHeight()
//            ) {
//                Text(text = "ADD")
//            }
        }

        Spacer(modifier = Modifier.height(Dp(1f)))

        Surface(modifier = Modifier.padding(all = Dp(5f))) {
            LazyColumn {

                itemsIndexed(notesList) { index, item ->

                    val annotatedText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Delete")
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(Dp(50f))
                    ) {

                        Text(text = item, Modifier.weight(0.85f))

                        ClickableText(
                            text = annotatedText, onClick = {

                                notesList.remove(item)

                            },
                            modifier = Modifier.weight(0.15f)
                        )
                    }
                }
            }
        }


    }


}
