package com.example.gametime.presentation.GameCircle.common

import androidx.compose.ui.unit.Dp

//28.03.2026
//Алексей
//класс данных для кругов
data class CircleItem(
    val id: Int,
    val size: Float,
    val border: Float,
    val x: Float,
    val y: Float,
    val isPlaced: Boolean = false
)