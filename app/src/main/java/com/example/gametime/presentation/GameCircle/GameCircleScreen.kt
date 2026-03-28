package com.example.gametime.presentation.GameCircle

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.example.gametime.Navigation
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
    val density = LocalDensity.current
    LaunchedEffect(Unit) {
        vm.onEvent(GameCircleEvent.GetGameInfo(gameId, winningPrice))
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver{ _, event ->
            if(event == Lifecycle.Event.ON_STOP){
                vm.onEvent(GameCircleEvent.Surrender)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
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
                    minutes = state.minutes,
                    seconds = state.seconds
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .onSizeChanged { size ->
                        vm.onEvent(
                            GameCircleEvent.InitGame(
                                centerX = size.width / 2f,
                                centerY = size.height / 2f
                            )
                        )
                    }) {

                    state.circles.forEach { circle ->

                        Box(
                            modifier = Modifier
                                .offset(
                                    x = with(density) { (circle.x - circle.size / 2).toDp() },
                                    y = with(density) { (circle.y - circle.size / 2).toDp() }
                                )
                                .size(with(density) { circle.size.toDp() })
                                .background(Color.White, CircleShape)
                                .border(
                                    width = with(density) { circle.border.toDp() },
                                    color = Color(0xFFF94B73),
                                    shape = CircleShape
                                )
                                .clickable {
                                    vm.onEvent(
                                        GameCircleEvent.CircleClicked(circle.id)
                                    )
                                }
                        )
                    }
                }

            }
        }
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(bottom = 69.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            MainButton(
                text = "Surrender",
                onCLick = {
                    vm.onEvent(GameCircleEvent.Surrender)
                }
            )
        }
    }
    if(state.isWin || state.gameEnd){
        SuccessfulMessage(
            onDismiss = {navController.navigate(Navigation.Landing)},
            onButtonClick = { navController.navigate(Navigation.DiscoverCombats)},
            title = if(state.isWin){
                "You Winner"
            } else{
                "You Loser"
            },
            text = "",
            buttonText = "Discover combats"
        )
    }
}