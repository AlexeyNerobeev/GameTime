package com.example.gametime.presentation.CombatInformation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.Navigation
import com.example.gametime.R
import com.example.gametime.common.PlayerAvatar
import com.example.gametime.common.SuccessfulMessage
import com.example.gametime.presentation.CombatInformation.common.ColumnData
import com.example.uikit.Black
import com.example.uikit.Buttons.MainButton
import com.example.uikit.Card
import com.example.uikit.CustomCheckBox
import com.example.uikit.Theme

//26.03.2026
//Алексей
//метод для отображения экрана CombatInformation. Принимает контроллер навигации, viewmodel,
////id игры
@Composable
fun CombatInformationScreen(navController: NavController, id: Int, vm: CombatVM = hiltViewModel()) {
    val state = vm.state.value
    LaunchedEffect(key1 = id) {
        vm.onEvent(CombatEvent.GetInfo(id))
    }
    val columnsList = listOf(
        ColumnData(
            title = "1ST POSITION",
            price = 2000
        ),
        ColumnData(
            title = "2ND POSITION",
            price = 1000
        ),
        ColumnData(
            title = "3RD POSITION",
            price = 500
        ),
        ColumnData(
            title = "4TH 5TH 6TH POSITION",
            price = 160
        )
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .blur(
                if (state.isJoin) {
                    14.dp
                } else {
                    0.dp
                }
            )
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
            LazyColumn(
                modifier = Modifier
                    .padding(top = 36.dp)
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Combat\n" +
                                    "Information",
                            color = Color(0xFFFA5075),
                            fontSize = 22.sp,
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(
                                Font(
                                    resId = com.example.uikit.R.font.poppins_bold
                                )
                            )
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
                                    .padding(top = 19.dp)
                                    .padding(start = 15.dp)
                                    .padding(end = 13.dp)
                                    .padding(bottom = 27.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = state.combatName,
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 12.sp,
                                        color = Black
                                    )
                                    Text(
                                        text = "$${state.price}",
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 12.sp,
                                        color = Color(0xFFFA5075)
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(top = 1.dp)
                                ) {
                                    Text(
                                        text = "Status: ",
                                        style = Theme.typography.caption2Regular,
                                        fontSize = 6.sp,
                                        color = Black
                                    )
                                    Text(
                                        text = state.status,
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 6.sp,
                                        color = Color(0xFFFA5075)
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(top = 31.dp)
                                        .padding(start = 8.dp)
                                        .padding(end = 11.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    PlayerAvatar(
                                        status = "Host",
                                        name = "Scott Brown"
                                    )
                                    Text(
                                        text = "VS",
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 17.sp,
                                        color = Color(0xFFFA5075),
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                    PlayerAvatar(
                                        status = "Guest",
                                        name = "Stone Stella"
                                    )
                                    Text(
                                        text = "+",
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 18.sp,
                                        color = Color(0xFFFA5075),
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                    PlayerAvatar(
                                        status = "Guest",
                                        name = "Teslar Fullar"
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(top = 23.dp)
                                        .padding(start = 7.dp)
                                        .padding(end = 11.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    PlayerAvatar(
                                        status = "Guest",
                                        name = "Shema Laset"
                                    )
                                    Text(
                                        text = "+",
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 18.sp,
                                        color = Color(0xFFFA5075),
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                    PlayerAvatar(
                                        status = "Guest",
                                        name = "Tobi Dubala"
                                    )
                                    Text(
                                        text = "+",
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 18.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(top = 9.dp)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                    ) {
                                    }
                                }
                            }
                        }
                        Text(
                            text = "DESCRIPTION",
                            color = Color(0xFFFA5075),
                            style = Theme.typography.caption2Bold,
                            fontSize = 6.sp,
                            modifier = Modifier
                                .padding(top = 27.dp)
                        )
                        Text(
                            text = state.description,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            color = Black,
                            style = Theme.typography.caption2Regular,
                            fontSize = 10.sp
                        )
                        Text(
                            text = "CATEGORY",
                            color = Color(0xFFFA5075),
                            style = Theme.typography.caption2Bold,
                            fontSize = 6.sp,
                            modifier = Modifier
                                .padding(top = 23.dp)
                        )
                        Text(
                            text = state.category,
                            modifier = Modifier
                                .padding(top = 8.dp),
                            color = Black,
                            style = Theme.typography.caption2Regular,
                            fontSize = 10.sp
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 23.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            columnsList.forEach {
                                Column(modifier = it.modifier) {
                                    Text(
                                        text = it.title,
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 6.sp,
                                        color = Color(0xFFFA5075)
                                    )
                                    Text(
                                        text = "$${it.price}",
                                        style = Theme.typography.caption2Regular,
                                        fontSize = 10.sp,
                                        color = Black,
                                        modifier = Modifier
                                            .padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 30.dp)
                                .padding(horizontal = 62.dp)
                                .fillMaxWidth()
                                .background(
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
                                    .padding(top = 17.dp)
                                    .padding(bottom = 19.dp)
                                    .padding(horizontal = 20.dp)
                                    .fillMaxWidth()
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(R.drawable.time_icon),
                                        contentDescription = null,
                                        tint = Color.Unspecified
                                    )
                                    Text(
                                        text = "TIME INTERVAL",
                                        style = Theme.typography.caption2Bold,
                                        fontSize = 10.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .padding(start = 4.dp)
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(top = 14.dp)
                                        .fillMaxWidth()
                                ) {
                                    Column {
                                        Text(
                                            text = "FROM",
                                            color = Color.White,
                                            style = Theme.typography.caption2Bold,
                                            fontSize = 6.sp
                                        )
                                        Text(
                                            text = state.dateFrom,
                                            style = Theme.typography.caption2Regular,
                                            color = Color.White,
                                            fontSize = 10.sp
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "FROM",
                                            color = Color.White,
                                            style = Theme.typography.caption2Bold,
                                            fontSize = 6.sp
                                        )
                                        Text(
                                            text = "12:30 AM",
                                            style = Theme.typography.caption2Regular,
                                            color = Color.White,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(top = 23.dp)
                                        .fillMaxWidth()
                                ) {
                                    Column {
                                        Text(
                                            text = "TO",
                                            color = Color.White,
                                            style = Theme.typography.caption2Bold,
                                            fontSize = 6.sp
                                        )
                                        Text(
                                            text = state.dateFrom,
                                            style = Theme.typography.caption2Regular,
                                            color = Color.White,
                                            fontSize = 10.sp
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "TO",
                                            color = Color.White,
                                            style = Theme.typography.caption2Bold,
                                            fontSize = 6.sp
                                        )
                                        Text(
                                            text = "3:30 AM",
                                            style = Theme.typography.caption2Regular,
                                            color = Color.White,
                                            fontSize = 10.sp
                                        )
                                    }
                                }
                            }
                        }
                        Text(
                            text = "REMINDERS",
                            style = Theme.typography.caption2Bold,
                            fontSize = 6.sp,
                            color = Color(0xFFFA5075),
                            modifier = Modifier
                                .padding(top = 30.dp)
                        )
                        Row(
                            modifier = Modifier
                                .padding(top = 9.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomCheckBox(
                                value = state.notification,
                                onValueChange = { vm.onEvent(CombatEvent.ChangeNotification) }
                            )
                            Text(
                                text = "Notification",
                                style = Theme.typography.caption2Regular,
                                fontSize = 10.sp,
                                color = Black,
                                modifier = Modifier
                                    .padding(start = 7.dp)
                            )
                        }
                        MainButton(
                            text = "Join Combat!",
                            onCLick = { vm.onEvent(CombatEvent.JoinCombat) },
                            modifier = Modifier
                                .padding(top = 45.dp)
                                .padding(bottom = 10.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
    if (state.isJoin) {
        SuccessfulMessage(
            onDismiss = {
                navController.navigate(Navigation.DiscoverCombats) {
                    popUpTo(0)
                }
            },
            title = "Successfully \n" +
                    "Join Combat",
            text = "Wanna know more\ninformation bout’ this\ncompetition?",
            buttonText = "Discover combats",
            onButtonClick = { navController.navigate(Navigation.DiscoverCombats) {
                popUpTo(0)
            } }
        )
    }
}