package com.example.gametime.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime.R
import com.example.uikit.Black
import com.example.uikit.Theme

//27.03.2026
//Алексей
//метод для отображения автара игрока
@Composable
fun PlayerAvatar(
    status: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
        Box(
            modifier = Modifier
                .size(43.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.avatar_icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .size(31.dp, 14.dp)
                    .offset(x = (-17).dp, y = (-6).dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFFF6480),
                                Color(0xFFF22E63)
                            )
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = status,
                    style = Theme.typography.caption2Regular,
                    fontSize = 6.sp,
                    color = Color.White
                )
            }
        }
        Text(
            text = name,
            style = Theme.typography.caption2Bold,
            fontSize = 8.sp,
            color = Black,
            modifier = Modifier
                .padding(top = 5.dp)
        )
    }
}