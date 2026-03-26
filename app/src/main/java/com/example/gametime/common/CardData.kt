package com.example.gametime.common

import androidx.compose.ui.Modifier

data class CardData(
    val title: String,
    val text: String,
    val image: Int,
    val onCLick: () -> Unit,
    val modifier: Modifier
)