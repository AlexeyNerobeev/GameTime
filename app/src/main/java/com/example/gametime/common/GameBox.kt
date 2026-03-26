package com.example.gametime.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime.R
import com.example.uikit.Black
import com.example.uikit.Theme

//26.03.2026
//Алексей
//метод для отображения карточки игры
@Composable
fun GameBox(
    name: String,
    status: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .height(64.dp)
            .border(
                1.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF6480),
                        Color(0xFFF22E63)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .padding(start = 16.dp)
                .padding(end = 9.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Box(
                        modifier = Modifier
                            .size(27.dp)
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
                                .size(20.dp, 9.dp)
                                .offset(x = (-10).dp, (-4).dp)
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
                        )
                        {
                            Text(
                                text = "Host",
                                style = Theme.typography.caption2Regular,
                                color = Color.White,
                                fontSize = 4.sp
                            )
                        }
                    }
                    Text(
                        text = "Scott Brown",
                        color = Black,
                        style = Theme.typography.caption2Bold,
                        fontSize = 6.sp,
                        modifier = Modifier
                            .padding(top = 3.dp)
                    )
                }
                Text(
                    text = "VS",
                    color = Color(0xFFFA5075),
                    style = Theme.typography.caption2Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                )
                Column {
                    Box(
                        modifier = Modifier
                            .size(27.dp)
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
                                .align(Alignment.TopEnd)
                                .size(20.dp, 9.dp)
                                .offset(x = 10.dp, (-4).dp)
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
                        )
                        {
                            Text(
                                text = "Guest",
                                style = Theme.typography.caption2Regular,
                                color = Color.White,
                                fontSize = 4.sp
                            )
                        }
                    }
                    Text(
                        text = "Stone Stella",
                        color = Black,
                        style = Theme.typography.caption2Bold,
                        fontSize = 6.sp,
                        modifier = Modifier
                            .padding(top = 3.dp)
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Game  Name:",
                        color = Black,
                        style = Theme.typography.caption2Regular,
                        fontSize = 6.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = name,
                        color = Color(0xFFFA5075),
                        style = Theme.typography.caption2Bold,
                        fontSize = 6.sp,
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 18.dp)) {
                    Text(
                        text = "Status:",
                        color = Black,
                        style = Theme.typography.caption2Regular,
                        fontSize = 6.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = status,
                        color = Color(0xFFFA5075),
                        style = Theme.typography.caption2Bold,
                        fontSize = 6.sp,
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 18.dp)) {
                    Text(
                        text = "Winning Price:",
                        color = Black,
                        style = Theme.typography.caption2Regular,
                        fontSize = 6.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "$$price",
                        color = Color(0xFFFA5075),
                        style = Theme.typography.caption2Bold,
                        fontSize = 6.sp,
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
            }
        }
    }
}