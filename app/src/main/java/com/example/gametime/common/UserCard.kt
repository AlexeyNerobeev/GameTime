package com.example.gametime.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

//26.03.2026
//Алексей
//метод для отображегия карточки пользователей
@Composable
fun UserCard(
    name: String,
    crown: String,
    status: String,
    category: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                1.dp, shape = RoundedCornerShape(10.dp),
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF6480),
                        Color(0xFFF22E63)
                    )
                )
            )
    ){
        Row(modifier = Modifier
            .padding(top = 11.dp)
            .padding(start = 11.dp)
            .padding(bottom = 14.dp)
            .padding(end = 17.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(R.drawable.avatar_icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(37.dp))
            Column(modifier = Modifier
                .padding(start = 7.dp)) {
                Text(text = name,
                    color = Black,
                    style = Theme.typography.caption2Bold,
                    fontSize = 6.sp)
                Row(modifier = Modifier
                    .padding(top = 1.dp),
                    verticalAlignment = Alignment.Bottom){
                    Icon(painter = painterResource(R.drawable.echelon_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(bottom = 1.dp)
                            .size(6.dp)
                    )
                    Text(text = crown,
                        style = Theme.typography.caption2Regular,
                        color = Color(0xFFF4C73E),
                        fontSize = 6.sp,
                        modifier = Modifier
                            .padding(start = 4.dp))
                }
                Row {
                    Text(text = "Status: ",
                        color = Black,
                        style = Theme.typography.caption2Regular,
                        fontSize = 6.sp)
                    Text(text = status,
                        color = Color(0xFFF4C73E),
                        style = Theme.typography.caption2Regular,
                        fontSize = 6.sp)
                }
                Row {
                    Text(text = "Category’s: ",
                        color = Black,
                        style = Theme.typography.caption2Regular,
                        fontSize = 6.sp)
                    Text(text = category,
                        color = Color(0xFFFF6480),
                        style = Theme.typography.caption2Regular,
                        fontSize = 6.sp)
                }
            }
        }
    }
}