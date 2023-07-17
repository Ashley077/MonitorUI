package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
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

@Composable

fun ConnectStatus(navController: NavController){

    Box(modifier = Modifier.fillMaxSize()){
        ScaffoldConnect(navController)
    }
}

@Composable
fun ScaffoldConnect(navController: NavController){
    Scaffold(
        topBar = { CustomTopAppBar(navController, "Connect Status", true)
        }, content = { it
            Column(modifier = Modifier.fillMaxSize().padding(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                modifier = Modifier.padding(20.dp),
                contentAlignment = Alignment.Center
                ){
                    Text(text = "Connnect Status", modifier = Modifier.align(Alignment.TopCenter),
                    style = TextStyle(fontSize = 50.sp,
                        fontFamily = FontFamily.Cursive,
                    ))
                }

                Spacer(modifier = Modifier.height(20.dp))
                DisplayConnectList()

            }
        }
    )
}

@Composable
fun DisplayConnectList() {
// this variable use to handle list state
    val notesList = remember {
        mutableStateListOf<String>()
    }
// this variable use to handle edit text input value
    val inputvalue = remember { mutableStateOf(TextFieldValue()) }

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .height(Dp(60f))
        ) {

            TextField(
                value = inputvalue.value,
                onValueChange = {
                    inputvalue.value = it
                },
                modifier = Modifier.weight(0.8f),
                placeholder = { Text(text = "Enter") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    color = Color.Black, fontSize = TextUnit.Unspecified,
                    fontFamily = FontFamily.SansSerif
                ),
                maxLines = 1,
                singleLine = true
            )

            Button(
                onClick = {
                    notesList.add(inputvalue.value.text)
                    inputvalue.value = TextFieldValue("")
                },
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight()
            ) {
                Text(text = "ADD")
            }
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
