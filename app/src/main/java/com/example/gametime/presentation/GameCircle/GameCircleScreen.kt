package com.example.gametime.presentation.GameCircle

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.common.SuccessfulMessage
import com.example.uikit.Buttons.MainButton
import com.example.uikit.Theme
import com.example.uikit.Timer

//28.03.2026
//Алексей
//метод для отображения экрана GameImage. Принимает контроллер навигации, id игры,
//сумма выигрыша и viewmodel
@Composable
fun GameCircleScreen(navController: NavController, gameId: Int, winningPrice: Int, vm: GameCircleVM = hiltViewModel()) {
    val state = vm.state.value
    LaunchedEffect(Unit) {
        vm.onEvent(GameCircleEvent.StartGame(100, 180))
    }
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
            Column(
                modifier = Modifier
                    .padding(top = 11.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Game  Circle",
                    color = Color(0xFFFA5075),
                    style = Theme.typography.caption2Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
                Timer(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth(),
                    minutes = 0,
                    seconds = 0
                )
                val density = LocalDensity.current
                // 🎯 Игровое поле
                BoxWithConstraints(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {

                    val widthPx = with(density) { maxWidth.toPx() }.toInt()
                    val heightPx = with(density) { maxHeight.toPx() }.toInt()

                    // 🚀 старт игры
                    LaunchedEffect(Unit) {
                        vm.onEvent(GameCircleEvent.StartGame(widthPx, heightPx))
                    }

                    Box(modifier = Modifier.fillMaxSize()) {

                        state.circles.forEach { circle ->

                            val xDp = with(density) { circle.x.toDp() }
                            val yDp = with(density) { circle.y.toDp() }

                            val isSelected = state.selected.contains(circle.id)

                            Box(
                                modifier = Modifier
                                    .offset(x = xDp, y = yDp)
                                    .size(circle.size.dp)
                                    .border(
                                        width = circle.borderWidth.dp,
                                        color = if (isSelected) Color.Gray else Color(0xFFF94B73),
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        vm.onEvent(GameCircleEvent.OnCircleClick(circle.id))
                                    }
                            )
                        }

                        // Центр (собранные круги)
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            val selectedCircles = state.selected.mapNotNull { id ->
                                state.circles.find { it.id == id }
                            }

                            selectedCircles.forEach { circle ->
                                Box(
                                    modifier = Modifier
                                        .size(circle.size.dp)
                                        .border(
                                            width = circle.borderWidth.dp,
                                            color = Color(0xFFF94B73),
                                            shape = CircleShape
                                        )
                                )
                            }
                        }
                    }
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 69.dp),
                    contentAlignment = Alignment.BottomCenter){
                    MainButton(
                        text = "Surrender",
                        onCLick = {

                        }
                    )
                }
            }
        }
    }
    if(state.isWin){
        SuccessfulMessage(
            onDismiss = {},
            onButtonClick = {},
            title = "You Winner",
            text = "",
            buttonText = "Discover combats"
        )
    }
}