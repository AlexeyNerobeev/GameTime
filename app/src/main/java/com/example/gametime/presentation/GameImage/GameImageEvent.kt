package com.example.gametime.presentation.GameImage

import android.graphics.Bitmap
import com.example.gametime.presentation.GameImage.common.PuzzlePiece

//27.03.2026
//Алексей
//класс событий для экрана GameImage
sealed class GameImageEvent {
    data class SplitImage(val value: Bitmap): GameImageEvent()
    data class OnPieceClick(val value: Int): GameImageEvent()
    data object Lose: GameImageEvent()
    data class GetGameInfo(val id: Int, val winningPrice: Int): GameImageEvent()
}