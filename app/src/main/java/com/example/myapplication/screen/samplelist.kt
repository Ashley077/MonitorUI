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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.geometry.Offset


import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.component.CustomTopAppBar
import androidx.compose.ui.graphics.Color.Companion.Gray
import java.nio.file.Files.copy
import java.util.Collections.copy

@Composable
fun samplelist(navController: NavController){
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

                Text(text = "Sample List",style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily.Cursive))
                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(50.dp)
                    ) {
                        Text(text = "test")
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .height(50.dp)
                    ) {
                        Text(text = "rain")
                    }
                }


            }
        }
    )
}
/*@Composable
fun CustomSeekbar(
    modifier: Modifier,
    onProgressChanged: (progress: Float) -> Unit
) {
    // 当前进度，范围0-1之间， 初始为0
    var progress by remember { mutableStateOf(0f) }
    // bar是否被按下
    var barPressed by remember{ mutableStateOf(false) }
    // 锚点的半径, 根据barPressed的状态'平滑'地改变自身的大小
    val radius by animateFloatAsState(if (barPressed) 30f else 20f)
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 进度的文本
        Text(text = (progress * 100).toInt().toString(), Modifier.width(30.dp))
        Canvas(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()
            .weight(1f)
            .padding(10.dp)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { barPressed = true },
                    onDragCancel = { barPressed = false },
                    onDragEnd = {

                        barPressed = false
                        onProgressChanged.invoke(progress)
                    },
                    onDrag = { change, dragAmount ->

                        progress = if (change.position.x < 0) {
                            0f
                        } else if (change.position.x > size.width) {
                            1f
                        } else {
                            (change.position.x / this.size.width)
                        }
                    })
            }
            .pointerInput(Unit) {

                detectTapGestures(onTap = {
                    progress = (it.x / size.width)
                    barPressed = false
                })
            },
            onDraw = {
                // 底部灰色线条
                drawLine(
                    start = Offset(0f, size.height / 2),
                    end = Offset(size.width, size.height / 2),
                    strokeWidth = 8f,
                    color = androidx.compose.ui.graphics.Color.Gray
                )
                // 顶部蓝色线条
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Blue,
                    start = Offset(0f, size.height / 2),
                    end = Offset(size.width * progress, size.height / 2),
                    strokeWidth = 12f
                )
                // 锚点
                drawCircle(
                    color = androidx.compose.ui.graphics.Color.Blue,
                    radius = radius,
                    center = Offset(size.width * progress, size.height / 2)
                )
            })
    }
}*/



