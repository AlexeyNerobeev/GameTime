package com.example.gametime.presentation.GameImage

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.gametime.presentation.GameImage.common.PuzzlePiece
import com.example.network.domain.model.Statistics
import com.example.network.domain.usecase.SaveGameResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

//28.03.2026
//Алексей
//viewmodel для экрана GameImage. Принимает usecase для сохранения результата игры и получения id
//текущего пользователя
@HiltViewModel
class GameImageVM @Inject constructor(
    private val saveGameResultUseCase: SaveGameResultUseCase,
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GameImageState())
    val state: State<GameImageState> = _state

    init {
        viewModelScope.launch {
            while (state.value.seconds > 0) {
                delay(1000)
                _state.value = state.value.copy(
                    seconds = state.value.seconds - 1
                )
            }
            if (state.value.seconds == 0 && state.value.minutes > 0) {
                _state.value = state.value.copy(
                    minutes = state.value.minutes - 1,
                    seconds = 60
                )
            }
            if (state.value.seconds == 0 && state.value.minutes == 0) {
                _state.value = state.value.copy(
                    gameEnd = true
                )
            }
        }
    }

    fun onEvent(event: GameImageEvent) {
        when (event) {
            is GameImageEvent.SplitImage -> {
                val shuffledPieces = splitImage(event.value).shuffled().toList()
                _state.value = state.value.copy(
                    pieces = shuffledPieces
                )
            }

            is GameImageEvent.OnPieceClick -> {
                val pieces = _state.value.pieces.toMutableList()

                val firstIndex = state.value.firstSelectedIndex

                if (firstIndex == null) {
                    _state.value = state.value.copy(
                        firstSelectedIndex = event.value
                    )
                } else {

                    pieces.swap(firstIndex, event.value)

                    _state.value = state.value.copy(
                        pieces = pieces,
                        firstSelectedIndex = null
                    )

                    val win = pieces.map { it.id } == (0..8).toList()
                    if (win) {
                        val userId = loadCurrentUserUseCase.invoke()
                        viewModelScope.launch(Dispatchers.IO) {
                            saveGameResultUseCase.invoke(
                                Statistics(
                                    gameId = state.value.gameId,
                                    result = "win",
                                    userId = userId,
                                    totalWinning = state.value.winningPrice
                                )
                            )
                            _state.value = state.value.copy(
                                passageStatusWin = true,
                                gameEnd = true
                            )
                        }
                    }
                }
            }
            GameImageEvent.Lose -> {
                val userId = loadCurrentUserUseCase.invoke()
                viewModelScope.launch(Dispatchers.IO) {
                    saveGameResultUseCase.invoke(
                        Statistics(
                            gameId = state.value.gameId,
                            result = "lose",
                            userId = userId,
                            totalWinning = state.value.winningPrice
                        )
                    )
                    _state.value = state.value.copy(
                        gameEnd = true
                    )
                }
            }
            is GameImageEvent.GetGameInfo -> {
                _state.value = state.value.copy(
                    gameId = event.id,
                    winningPrice = event.winningPrice
                )
            }
        }
    }

    private fun splitImage(bitmap: Bitmap): List<PuzzlePiece> {
        val pieces = mutableListOf<PuzzlePiece>()
        val pieceWidth = bitmap.width / 3
        val pieceHeight = bitmap.height / 3

        var id = 0
        for (row in 0 until 3) {
            for (col in 0 until 3) {
                val piece = Bitmap.createBitmap(
                    bitmap,
                    col * pieceWidth,
                    row * pieceHeight,
                    pieceWidth,
                    pieceHeight
                )
                pieces.add(PuzzlePiece(id++, piece))
            }
        }
        return pieces
    }

    private fun <T> MutableList<T>.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}