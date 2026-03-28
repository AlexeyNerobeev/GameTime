package com.example.gametime.presentation.GameCircle.common

//28.03.2026
//Алексей
//класс данных для кругов
data class CircleItem(
    val id: Int,
    val size: Int,
    val borderWidth: Int,
    val correctOrder: Int,
    val x: Float = 0f,
    val y: Float = 0f
)