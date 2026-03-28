package com.example.gametime.presentation.GameImage

import com.example.gametime.presentation.GameImage.common.PuzzlePiece

//28.03.2026
//Алексей
//класс состояний для экрана GameImage
data class GameImageState(
    val pieces: List<PuzzlePiece> = listOf(),
    val firstSelectedIndex: Int? = null,
    val gameEnd: Boolean = false,
    val passageStatusWin: Boolean = false,
    val minutes: Int = 0,
    val seconds: Int = 60,
    val gameId: Int = 0,
    val winningPrice: Int = 0
)