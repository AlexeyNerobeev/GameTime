package com.example.gametime.presentation.CombatInformation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uikit.Black
import com.example.uikit.Theme

//27.03.2026
//Алексей
//класс данных для колонки призовых за место
data class ColumnData(
    val title: String,
    val price: Int,
    val modifier: Modifier = Modifier
)