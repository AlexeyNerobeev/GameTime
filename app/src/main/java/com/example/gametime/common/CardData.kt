package com.example.gametime.common

import androidx.compose.ui.Modifier

//27.03.2026
//Алексей
//класс данных для card
data class CardData(
    val title: String,
    val text: String,
    val image: Int,
    val onCLick: () -> Unit,
    val modifier: Modifier
)