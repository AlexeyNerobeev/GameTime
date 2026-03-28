package com.example.gametime.presentation.GameCircle

import com.example.gametime.presentation.GameCircle.common.CircleItem

//28.03.2026
//Алексей
//класс состояний для экрана gameCircle
data class GameCircleState(
    val circles: List<CircleItem> = emptyList(),
    val selected: List<Int> = emptyList(),
    val isStarted: Boolean = false,
    val isWin: Boolean = false
)