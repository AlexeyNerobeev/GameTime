package com.example.gametime.presentation.PlayerInformation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gametime.R
import com.example.gametime.common.GameBox
import com.example.uikit.Black
import com.example.uikit.Theme

//27.03.2026
//Алексей
//метод для отображения экрана PlayerInformation. Принимает контроллер навигации
@Composable
fun PlayerInformationScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(start = 30.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 36.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Player\n" +
                            "Information",
                    style = Theme.typography.caption2Bold,
                    fontSize = 22.sp,
                    color = Color(0xFFFA5075)
                )
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFFFF6480),
                                    Color(0xFFF22E63)
                                )
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(bottom = 45.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(76.dp)
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
                                    .padding(end = 4.dp)
                                    .size(16.dp)
                                    .background(
                                        color = Color(0xFF08F403),
                                        shape = CircleShape
                                    )
                            )
                        }
                        Text(
                            text = "Scott Brown",
                            color = Black,
                            style = Theme.typography.caption2Bold,
                            modifier = Modifier
                                .padding(top = 9.dp)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Status: ",
                                color = Black,
                                style = Theme.typography.caption2Regular,
                                fontSize = 8.sp
                            )
                            Text(
                                text = "Online",
                                style = Theme.typography.caption2Bold,
                                fontSize = 8.sp,
                                color = Color(0xFF08F403)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 11.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Earned:",
                                    style = Theme.typography.caption2Regular,
                                    fontSize = 10.sp,
                                    color = Black
                                )
                                Text(
                                    text = "$5000",
                                    style = Theme.typography.caption2Bold,
                                    fontSize = 10.sp,
                                    color = Color(0xFFFA5075)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .padding(start = 5.dp)
                                    .padding(end = 4.dp)
                                    .size(1.dp, 26.dp)
                                    .background(color = Color(0xFFC9C9C9))
                            )
                            Column {
                                Text(
                                    text = "Staked:",
                                    style = Theme.typography.caption2Regular,
                                    fontSize = 10.sp,
                                    color = Black
                                )
                                Text(
                                    text = "$2000",
                                    style = Theme.typography.caption2Bold,
                                    fontSize = 10.sp,
                                    color = Color(0xFFFA5075)
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(top = 30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.echelon_icon),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier
                                    .size(29.dp)
                            )
                            Text(
                                text = "Gold Player",
                                style = Theme.typography.caption2Regular,
                                fontSize = 18.sp,
                                color = Color(0xFFF4C73E),
                                modifier = Modifier
                                    .padding(start = 9.dp)
                            )
                        }
                    }
                }
                Text(
                    text = "CATEGORY",
                    style = Theme.typography.caption2Bold,
                    fontSize = 6.sp,
                    color = Color(0xFFFA5075),
                    modifier = Modifier
                        .padding(top = 21.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 9.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(59.dp, 20.dp)
                            .border(
                                1.dp,
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFFF6480),
                                        Color(0xFFF22E63)
                                    )
                                ),
                                shape = RoundedCornerShape(3.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "Image",
                            style = Theme.typography.caption2Regular,
                            fontSize = 10.sp,
                            color = Black)
                    }
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(59.dp, 20.dp)
                            .border(
                                1.dp,
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFFFF6480),
                                        Color(0xFFF22E63)
                                    )
                                ),
                                shape = RoundedCornerShape(3.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "Circles",
                            style = Theme.typography.caption2Regular,
                            fontSize = 10.sp,
                            color = Black)
                    }
                }
                Text(text = "Player\n" +
                        "Combats",
                    style = Theme.typography.caption2Bold,
                    fontSize = 14.sp,
                    color = Color(0xFFFA5075),
                    modifier = Modifier
                        .padding(top = 16.dp))
                LazyColumn(modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()) {
                    items(3){
                        GameBox(
                            name = "Halo 5",
                            status = "Open",
                            price = "4,000",
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )
                    }
                }
            }
        }
    }
}