package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable

import androidx.compose.foundation.text.ClickableText

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.AnnotatedString

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.component.CustomTopAppBar

@Composable

fun HistoricalRecord(navController: NavController){

    Box(modifier = Modifier.fillMaxSize()){
        scaffoldhistory(navController)
    }
}


@Composable
fun scaffoldhistory(navController: NavController){
    Scaffold(
        topBar = { CustomTopAppBar(navController, "Historical record", true)
        }, content = { it
            Box(modifier = Modifier.fillMaxSize()) {
                ClickableText(
                    text = AnnotatedString("Historial record"),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(20.dp),
                    onClick = {},
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontFamily = FontFamily.Cursive,
                    )
                )
            }
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Spacer(modifier = Modifier.height(20.dp))
                RadioButton()
            }
        }
    )
}
@Composable
fun RadioButton() {
    val radioOptions = listOf("rain", "boil water", "cat")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}





