package com.example.myapplication.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.Routes
import com.example.myapplication.component.CustomTopAppBar
import com.example.myapplication.ui.theme.Purple700

@Composable
fun Loading(navController: NavHostController){
    Box(modifier = Modifier.fillMaxSize()){
        scaffoldLoading(navController)
    }
}
@Composable
fun scaffoldLoading(navController: NavHostController,
         circleColors: List<Color> = listOf(
            Color(0xFF5851D8),
            Color(0xFF833AB4),
            Color(0xFFC13584),
            Color(0xFFE1306C),
            Color(0xFFFD1D1D),
            Color(0xFFF56040),
            Color(0xFFF77737),
            Color(0xFFFCAF45),
            Color(0xFFFFDC80),
            Color(0xFF5851D8)
    )) {
        Scaffold(topBar = {CustomTopAppBar(navController, "Loading", true)
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                ClickableText(
                    text = AnnotatedString("Next"),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    onClick = { navController.navigate(Routes.Voice.route) },
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
                // progress value
                var progress by remember {
                    mutableStateOf(0f) // initially 0f
                }

                // animation
                val progressAnimate by animateFloatAsState(
                    targetValue = progress,
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                )


                CircularProgressIndicator(
                    modifier = Modifier
                        .size(size = 64.dp)
                        .border(
                            width = 2.dp,
                            brush = Brush.sweepGradient(circleColors),
                            shape = CircleShape
                        ),
                    progress = progressAnimate,
                    strokeWidth = 1.dp,
                    color = MaterialTheme.colors.background, // Set background color
                )

                // this is called when the Activity is launched
                LaunchedEffect(Unit) {
                    progress = 0.6f
                }

                // add space between indicator and button
                Spacer(modifier = Modifier.height(height = 16.dp))

                // button

            }
        })
}


/*@Composable
fun LoadingAnimation(navController: NavHostController,
    indicatorSize: Dp = 100.dp,
    circleColors: List<Color> = listOf(
        Color(0xFF5851D8),
        Color(0xFF833AB4),
        Color(0xFFC13584),
        Color(0xFFE1306C),
        Color(0xFFFD1D1D),
        Color(0xFFF56040),
        Color(0xFFF77737),
        Color(0xFFFCAF45),
        Color(0xFFFFDC80),
        Color(0xFF5851D8)
    ),
    animationDuration: Int = 100
) {
    Scaffold(topBar = {
        CustomTopAppBar(navController, "Loading", true)
    }, content = {
        Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Next"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate(Routes.Voice.route)},
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
            val infiniteTransition = rememberInfiniteTransition()
            var progress by remember {
                mutableStateOf(0f) // initially 0f
            }

            val progressAnimate by animateFloatAsState(
                targetValue = progress,
                animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
            )

            val rotateAnimation by infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDuration,
                        easing = LinearEasing
                    )
                ),

            )

            CircularProgressIndicator(
                modifier = Modifier
                    .size(size = indicatorSize)
                    .rotate(degrees = rotateAnimation)
                    .border(
                        width = 2.dp,
                        brush = Brush.sweepGradient(circleColors),
                        shape = CircleShape
                    ),
                progress = progressAnimate,
                strokeWidth = 1.dp,
                color = MaterialTheme.colors.background , // Set background color
            )
            LaunchedEffect(Unit) {
                progress = 0.6f
            }

            Button(
                onClick = {
                    progress = 1f
                    navController.navigate(Routes.Voice.route)
                }
            ){
                Text(
                    text = "Next",
                    color = Color.Blue
                )
            }
        }


    })
}*/


