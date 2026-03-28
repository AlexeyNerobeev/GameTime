package com.example.gametime.presentation.GameImage

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.gametime.Navigation
import com.example.gametime.R
import com.example.gametime.common.SuccessfulMessage
import com.example.gametime.presentation.GameImage.common.PuzzlePiece
import com.example.uikit.Buttons.MainButton
import com.example.uikit.Theme
import com.example.uikit.Timer
import kotlin.random.Random

//28.03.2026
//Алексей
//метод для отображения экрана GameImage. Принимает контроллер навигации, id игры,
//сумма выигрыша и viewmodel
@Composable
fun GameImageScreen(navController: NavController, gameId: Int, winningPrice: Int, vm: GameImageVM = hiltViewModel()) {
    val state = vm.state.value
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        vm.onEvent(GameImageEvent.GetGameInfo(gameId, winningPrice))
        val bitmap = BitmapFactory.decodeResource(
            context.resources,
            when(Random.nextInt(4)){
                1 -> R.drawable.puzzle_image
                2 -> R.drawable.puzzle_car_image
                3 -> R.drawable.puzzle_owl_image
                else -> R.drawable.puzzle_image
            }
        )
        vm.onEvent(GameImageEvent.SplitImage(bitmap))
    }
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver{ _, event ->
            if(event == Lifecycle.Event.ON_STOP){
                vm.onEvent(GameImageEvent.Lose)
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
            .blur(if(state.gameEnd){
                14.dp
            } else{
                0.dp
            })
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
                    text = "Game Image",
                    style = Theme.typography.caption2Bold,
                    fontSize = 22.sp,
                    color = Color(0xFFFA5075),
                    textAlign = TextAlign.Center
                )
                Timer(
                    modifier = Modifier
                        .padding(top = 30.dp),
                    seconds = state.seconds,
                    minutes = state.minutes
                )
                PuzzleGrid(
                    pieces = state.pieces,
                    onClick = {
                        vm.onEvent(GameImageEvent.OnPieceClick(it))
                    },
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .fillMaxWidth()
                        .heightIn(416.dp)
                )
                MainButton(
                    text = "Surrender",
                    onCLick = {
                        vm.onEvent(GameImageEvent.Lose)
                    },
                    modifier = Modifier
                        .padding(top = 26.dp)
                )
            }
        }
    }
    if(state.gameEnd){
        SuccessfulMessage(
            onDismiss = { navController.navigate(Navigation.Landing)},
            onButtonClick = { navController.navigate(Navigation.DiscoverCombats) },
            title = if(state.passageStatusWin){
                "You Winner"
            } else{
                "You Loser"
            },
            text = "",
            buttonText = "Discover combats"
        )
    }
}

//28.03.2026
//Алексей
//метод для отображения игры Image. Принимает список из кусочков пазла, метод нажатия на кусочек пазла
// и модификатор
@Composable
fun PuzzleGrid(
    pieces: List<PuzzlePiece>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        modifier = modifier) {
        itemsIndexed(pieces) { index, piece ->
            Image(
                bitmap = piece.bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onClick(index) }
            )
        }
    }
}