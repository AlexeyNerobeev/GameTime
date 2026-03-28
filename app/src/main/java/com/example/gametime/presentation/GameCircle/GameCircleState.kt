package com.example.gametime.presentation.GameCircle

import androidx.compose.ui.unit.Dp
import com.example.gametime.presentation.GameCircle.common.CircleItem

//28.03.2026
//Алексей
//класс состояний для экрана gameCircle
data class GameCircleState(
    val circles: List<CircleItem> = emptyList(),
    val nextExpectedSize: Float = 0f,
    val placedCount: Int = 0,
    val isWin: Boolean = false,
    val startX: Float = 0f,
    val startY: Float = 0f,
    val centerX: Float = 0f,
    val centerY: Float = 0f,
    val isPreview: Boolean = true,
    val minutes: Int = 0,
    val seconds: Int = 60,
    val gameEnd: Boolean = false,
    val gameId: Int = 0,
    val winningPrice: Int = 0
)