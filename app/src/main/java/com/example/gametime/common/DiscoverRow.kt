package com.example.gametime.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime.R
import com.example.uikit.Black
import com.example.uikit.Theme

//26.03.2026
//Алексей
//метод для отображения строки view all
@Composable
fun DiscoverRow(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(
                Font(
                    resId = com.example.uikit.R.font.poppins_bold
                )
            ),
            fontWeight = FontWeight(700),
            fontSize = 12.sp,
            color = Color(0xFFFA5075)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "View All",
                color = Black,
                style = Theme.typography.caption2Regular,
                fontSize = 8.sp
            )
            Icon(
                painter = painterResource(R.drawable.access_icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(start = 5.dp)
            )
        }
    }
}