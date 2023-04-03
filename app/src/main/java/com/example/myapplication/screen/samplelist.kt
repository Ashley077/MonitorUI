package com.example.myapplication.screen

import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue


import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.component.CustomTopAppBar
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import java.nio.file.Files.copy
import java.util.Collections.copy

@Composable

fun SampleList(navController: NavController){

    Box(modifier = Modifier.fillMaxSize()){
        scaffoldsample(navController)
    }
}

@Composable
fun scaffoldsample(navController: NavController){
    Scaffold(
        topBar = {CustomTopAppBar(navController, title = "Sample List", true)
        }, content = {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier.padding(20.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Sample List", modifier = Modifier.align(Alignment.TopCenter),
                        style = TextStyle(fontSize = 50.sp,
                            fontFamily = FontFamily.Cursive,
                        ))
                }
                Spacer(modifier = Modifier.height(20.dp))
                CheckableRow()



            }
        }
    )
}
@Composable

fun CheckableRow() {
    MaterialTheme {
        var checked by remember { mutableStateOf(false) }
        var checka by remember { mutableStateOf(false) }
        var checkb by remember { mutableStateOf(false) }
        var checkc by remember { mutableStateOf(false) }
        Row(
            Modifier
                .toggleable(
                    value = checked,
                    role = Role.Checkbox,
                    onValueChange = { checked = !checked }
                )
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("rain", Modifier.weight(1f))
            Checkbox(checked = checked, onCheckedChange = null)
        }
        Row(
            Modifier
                .toggleable(
                    value = checka,
                    role = Role.Checkbox,
                    onValueChange = { checka = !checka }
                )
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("boil water", Modifier.weight(1f))
            Checkbox(checked = checka, onCheckedChange = null)
        }
        Row(
            Modifier
                .toggleable(
                    value = checkb,
                    role = Role.Checkbox,
                    onValueChange = { checkb = !checkb }
                )
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("cat", Modifier.weight(1f))
            Checkbox(checked = checkb, onCheckedChange = null)
        }
        Row(
            Modifier
                .toggleable(
                    value = checkc,
                    role = Role.Checkbox,
                    onValueChange = { checkc = !checkc }
                )
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("rain", Modifier.weight(1f))
            Checkbox(checked = checkc, onCheckedChange = null)
        }
    }
}



