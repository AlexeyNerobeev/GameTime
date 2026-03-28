package com.example.gametime.presentation.GameCircle

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.gametime.presentation.GameCircle.common.CircleConfig
import com.example.gametime.presentation.GameCircle.common.CircleItem
import com.example.network.domain.model.Statistics
import com.example.network.domain.usecase.SaveGameResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

//28.03.2026
//Алексей
//viewmodel для экрана GameCircle. Принимает usecase для сохранения результатов игры,
//получения id текущего пользователя
@HiltViewModel
class GameCircleVM @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val saveGameResultUseCase: SaveGameResultUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GameCircleState())
    val state: State<GameCircleState> = _state

    init {
        viewModelScope.launch {
            while (state.value.seconds > 0) {
                delay(1000)
                val seconds = state.value.seconds - 1
                _state.value = state.value.copy(
                    seconds = seconds
                )
            }
            if (state.value.seconds == 0) {
                _state.value = state.value.copy(
                    gameEnd = true
                )
                saveResult("lose")
            }
        }
    }

    fun onEvent(event: GameCircleEvent) {
        when (event) {
            is GameCircleEvent.InitGame -> {
                initGame(event.centerX, event.centerY)
            }

            is GameCircleEvent.CircleClicked -> {
                handleClick(event.circleId)
            }

            GameCircleEvent.Surrender -> {
                _state.value = _state.value.copy(
                    gameEnd = true
                )
                saveResult("lose")
            }

            is GameCircleEvent.GetGameInfo -> {
                _state.value = state.value.copy(
                    gameId = event.id,
                    winningPrice = event.winningPrice
                )
            }
        }
    }

    private fun initGame(centerX: Float, centerY: Float) {

        val circleConfigs = listOf(
            CircleConfig(350f, 30f),
            CircleConfig(270f, 12f),
            CircleConfig(228f, 12f),
            CircleConfig(194f, 6f),
            CircleConfig(172f, 6f),
            CircleConfig(148f, 6f),
            CircleConfig(126f, 6f),
            CircleConfig(104f, 6f),
            CircleConfig(84f, 6f)
        )

        val configs = circleConfigs.sortedByDescending { it.size }

        val solvedCircles = configs.mapIndexed { i, config ->
            CircleItem(
                id = i,
                size = config.size,
                border = config.border,
                x = centerX,
                y = centerY,
                isPlaced = true
            )
        }

        _state.value = GameCircleState(
            circles = solvedCircles,
            nextExpectedSize = configs.first().size,
            placedCount = 0,
            isWin = false,
            startX = centerX,
            startY = centerY,
            isPreview = true
        )

        viewModelScope.launch {

            delay(800)

            val radiusBase = 420f

            val offsets = List(configs.size) {
                val angle = Math.random() * 2 * Math.PI
                val radius = (180..radiusBase.toInt()).random()

                (kotlin.math.cos(angle) * radius).toFloat() to
                        (kotlin.math.sin(angle) * radius).toFloat()
            }

            val scattered = configs.mapIndexed { i, config ->
                CircleItem(
                    id = i,
                    size = config.size,
                    border = config.border,
                    x = centerX + offsets[i].first,
                    y = centerY + offsets[i].second,
                    isPlaced = false
                )
            }

            _state.value = _state.value.copy(
                circles = scattered,
                isPreview = false
            )
        }
    }

    private fun handleClick(circleId: Int) {

        val current = _state.value
        val circles = current.circles.toMutableList()

        val index = circles.indexOfFirst { it.id == circleId }
        if (index == -1) return

        val circle = circles[index]

        if (circle.isPlaced) return
        if (circle.size != current.nextExpectedSize) return

        circles[index] = circle.copy(
            x = current.startX,
            y = current.startY,
            isPlaced = true
        )

        val remaining = circles.filter { !it.isPlaced }
        val nextSize = remaining.maxByOrNull { it.size }?.size ?: 0f

        val newPlaced = current.placedCount + 1
        val win = remaining.isEmpty()

        _state.value = current.copy(
            circles = circles,
            nextExpectedSize = nextSize,
            placedCount = newPlaced,
            isWin = win
        )

        if (win) {
            saveResult("win")
        }
    }

    private fun saveResult(status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = loadCurrentUserUseCase.invoke()
            saveGameResultUseCase.invoke(
                Statistics(
                    gameId = state.value.gameId,
                    result = status,
                    userId = userId,
                    totalWinning = state.value.winningPrice
                )
            )
        }
    }
}