package com.example.myapplication.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.selection.toggleable

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.semantics.Role

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.myapplication.TodoItem
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
        }, content = {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Text(text = "Historical Record",style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily.Cursive))
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
            Text("3/22 Rain", Modifier.weight(1f))
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
            Text("3/24 Boil Water", Modifier.weight(1f))
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
            Text("3/24 Door", Modifier.weight(1f))
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
            Text("3/25 Cat", Modifier.weight(1f))
            Checkbox(checked = checkc, onCheckedChange = null)
        }
    }
}



