package com.example.gametime.presentation.Statistics

import androidx.activity.contextaware.ContextAware
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.testing.invoke
import com.example.gametime.R
import com.example.uikit.Theme
import kotlinx.coroutines.delay

//27.03.2026
//Алексей
//метод для отображения экрана Statistics. Принимает контроллер навигации и viewmodel
@Composable
fun StatisticsScreen(navController: NavController, vm: StatisticsVM = hiltViewModel()) {
    val state = vm.state.value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 30.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 36.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Statistics",
                    style = Theme.typography.caption2Bold,
                    fontSize = 22.sp,
                    color = Color(0xFFFA5075)
                )
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFFF6480),
                                    Color(0xFFF22E63)
                                )
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
                ){
                    Column(modifier = Modifier
                        .padding(top = 13.dp)
                        .padding(start = 16.dp)
                        .padding(end = 23.dp)
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()) {
                        Text(text = "THIS WEEK EARNINGS",
                            style = Theme.typography.caption2Regular,
                            color = Color.White)
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(text = state.earnings.toString(),
                                style = Theme.typography.caption2Bold,
                                fontSize = 30.sp,
                                color = Color.White)
                            Icon(painter = painterResource(R.drawable.stats_icon),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(start = 23.dp))
                        }
                    }
                }
                Text(text = "Played Games",
                    style = Theme.typography.caption2Bold,
                    fontSize = 12.sp,
                    color = Color(0xFFFA5075),
                    modifier = Modifier
                        .padding(top = 26.dp))
                DonutChart(
                    pinkPercent = state.winsInImagePercent,
                    bluePercent = state.winsInCirclePercent,
                    winsPercent = state.totalWinsPercent,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun DonutChart(
    pinkPercent: Float,
    bluePercent: Float,
    winsPercent: Float,
    modifier: Modifier = Modifier
) {
    val pinkAnimated by animateFloatAsState(pinkPercent, animationSpec = tween())
    val blueAnimated by animateFloatAsState(bluePercent, animationSpec = tween())

    val gapAngle = 18f
    val pinkSweep = 360f * pinkAnimated - gapAngle
    val blueSweep = 360f * blueAnimated - gapAngle

    Box(
        modifier = modifier
            .padding(top = 35.dp)
            .size(300.dp),
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            val pinkStroke = 70.dp.toPx()
            val blueStroke = 55.dp.toPx()
            val bgStroke = 25.dp.toPx()

            val center = size.center
            val baseRadius = size.minDimension / 2

            // 🔘 серый
            val bgRadius = baseRadius - bgStroke / 2

            // 🔴 красный — ПО ЦЕНТРУ серого
            val pinkRadius = bgRadius

            // 🔵 синий — ПО ЦЕНТРУ красного
            val blueRadius = pinkRadius

            val bgRect = Rect(center = center, radius = bgRadius)
            val pinkRect = Rect(center = center, radius = pinkRadius)
            val blueRect = Rect(center = center, radius = blueRadius)

            // 🔘 ФОН
            drawArc(
                color = Color(0xFFE5E5E5),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = bgStroke),
                topLeft = bgRect.topLeft,
                size = bgRect.size
            )

            // 🔴 РОЗОВЫЙ
            drawArc(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0x80F22E63),
                        Color(0xD8F22E63)
                    ),
                    start = pinkRect.topCenter,
                    end = pinkRect.bottomCenter
                ),
                startAngle = 320f,
                sweepAngle = pinkSweep,
                useCenter = false,
                style = Stroke(
                    width = pinkStroke,
                    cap = StrokeCap.Butt
                ),
                topLeft = pinkRect.topLeft,
                size = pinkRect.size
            )

            // 🔵 СИНИЙ
            drawArc(
                brush = Brush.linearGradient(
                    listOf(
                        Color(0xFFD2EFFF),
                        Color(0xFF5FB1DF)
                    ),
                    start = blueRect.topLeft,
                    end = blueRect.bottomRight
                ),
                startAngle = -90f,
                sweepAngle = blueSweep,
                useCenter = false,
                style = Stroke(
                    width = blueStroke,
                    cap = StrokeCap.Square
                ),
                topLeft = blueRect.topLeft,
                size = blueRect.size
            )
        }

        Text(
            text = "$winsPercent %",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun donutPrev(){

    var state by remember { mutableStateOf(0f) }
    var state1 by remember { mutableStateOf(0f) }
    LaunchedEffect(Unit) {

        while (true) {
            delay(50)

            state += 0.01f
            state1 += 0.01f
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        DonutChart(.5f,.3f, .8f)
    }
}
