package com.example.gametime.presentation.Landing.common

import androidx.compose.ui.Modifier

//23.03.2026
//Алексей
//класс данных строки для sidemenu
data class RowData(
    val icon: Int,
    val text: String,
    val onClick: () -> Unit,
    val modifier: Modifier
)
