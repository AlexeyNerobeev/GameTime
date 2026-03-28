package com.example.gametime.presentation.Statistics

import com.example.network.domain.model.Statistics

//27.03.2026
//Алексей
//класс состояний для экрана Statistics
data class StatisticsState(
    val earnings: Int = 0,
    val winsInImagePercent: Float = 0f,
    val winsInCirclePercent: Float = 0f,
    val totalWinsPercent: Float = 0f,
    val lastWeekGames: List<Float> = listOf()
)