package com.example.gametime.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
//25.03.2026
//Алексей
//класс данных для InpputTF
data class InputTFData(
    val modifier: Modifier,
    val value: String,
    val onValueChange: (String) -> Unit,
    val placeholder: String,
    val withTrailingIcon: Boolean
)
