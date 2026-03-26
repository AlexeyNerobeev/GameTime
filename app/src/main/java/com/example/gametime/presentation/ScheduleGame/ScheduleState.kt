package com.example.gametime.presentation.ScheduleGame

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState

//26.03.2026
//Алексей
//класс состоний для экрана ScheduleGame
data class ScheduleState(
    val name: String = "",
    val category: String = "",
    val winningPrice: String = "",
    val dateFrom: String = "MON, NOV 4,2019",
    val timeFrom: String = "12:30 AM",
    val dateTo: String = "MON, NOV 9,2019",
    val timeTo: String = "12:30 AM",
    val description: String = "",
    val notification: Boolean = true,
    val isComplete: Boolean = false
)