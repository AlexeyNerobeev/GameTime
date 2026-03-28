package com.example.gametime.presentation.GameCircle

import android.R.attr.height
import android.R.attr.width
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.gametime.presentation.GameCircle.common.CircleItem
import com.example.network.domain.usecase.SaveGameResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//28.03.2026
//Алексей
//viewmodel для экрана GameCircle. Принимает usecase для сохранения результатов игры,
//получения id текущего пользователя
@HiltViewModel
class GameCircleVM @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val saveGameResultUseCase: SaveGameResultUseCase
): ViewModel() {
    private val _state = mutableStateOf(GameCircleState())
    val state: State<GameCircleState> = _state

    fun onEvent(event: GameCircleEvent){
        when(event){
            is GameCircleEvent.OnCircleClick -> {
                val current = _state.value
                if (current.selected.contains(event.id)) return
                val newSelected = current.selected + event.id
                _state.value = state.value.copy(
                    selected = newSelected
                )
                checkWin(newSelected)
            }
            is GameCircleEvent.StartGame -> {
                val base = listOf(
                    CircleItem(0, 150, 15, 0),
                    CircleItem(1, 110, 6, 1),
                    CircleItem(2, 89, 6, 2),
                    CircleItem(3, 72, 3, 3),
                    CircleItem(4, 61, 3, 4),
                    CircleItem(5, 49, 3, 5),
                    CircleItem(6, 38, 3, 6),
                    CircleItem(7, 17, 3, 7),
                )

                val randomized = base.map {
                    it.copy(
                        x = (0..(width - it.size)).random().toFloat(),
                        y = (0..(height - it.size)).random().toFloat()
                    )
                }

                _state.value = GameCircleState(
                    circles = randomized,
                    isStarted = true
                )
            }
        }
    }

    private fun checkWin(selected: List<Int>) {
        val circles = _state.value.circles

        val selectedCircles = selected.map { id ->
            circles.first { it.id == id }
        }

        val isCorrect = selectedCircles
            .zipWithNext()
            .all { (a, b) -> a.size >= b.size }

        if (selected.size == circles.size && isCorrect) {
            _state.value = _state.value.copy(isWin = true)
        }
    }
}