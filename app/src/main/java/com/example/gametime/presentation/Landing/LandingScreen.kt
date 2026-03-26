package com.example.gametime.presentation.Landing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.gametime.common.CardData
import com.example.gametime.presentation.Landing.common.RowData
import com.example.uikit.Black
import com.example.uikit.BottomBar
import com.example.uikit.Card
import com.example.uikit.Theme

//23.03.2026
//Алексей
//метод для отображения экрана landing. Принимает контроллер навигации и viewmodel
@Composable
fun LandingScreen(navController: NavController, vm: LandingVM = hiltViewModel()) {
    val state = vm.state.value
    LaunchedEffect(key1 = state.isLogout) {
        if(state.isLogout){
            navController.navigate(Navigation.SignIn){
                popUpTo(0)
            }
        }
    }
    val cardList = listOf(
        CardData(
            title = "Schedule",
            text = "Easily schedule event/games\n" +
                    "then find like minded players\n" +
                    "for battle. You up for it?",
            image = R.drawable.schedule_image,
            onCLick = { },
            modifier = Modifier
                .padding(top = 35.dp)
        ),
        CardData(
            title = "Statistics",
            text = "All data from previous and \n" +
                    "upcoming games can be found here ",
            image = R.drawable.statistics_image,
            onCLick = { },
            modifier = Modifier
                .padding(top = 10.dp)
        ),
        CardData(
            title = "Discover  Combats",
            text = "Find out what’s new and compete\n" +
                    "among players with new\n" +
                    "challenges and earn cash with\n" +
                    "game points ",
            image = R.drawable.discovercombats_image,
            onCLick = { },
            modifier = Modifier
                .padding(top = 10.dp)
        ),
        CardData(
            title = "Message Players",
            text = "Found the profile of a player\n" +
                    "that interests you? Start a\n" +
                    "conversation",
            image = R.drawable.messageplayers_image,
            onCLick = { },
            modifier = Modifier
                .padding(top = 10.dp)
        )
    )
    if (!state.showSideMenu) {
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
                Row(
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .padding(start = 30.dp)
                        .padding(end = 27.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(R.drawable.hamburger_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .clickable {
                                vm.onEvent(LandingEvent.ShowSideMenu)
                            }
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.avatar_icon),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Text(
                            text = state.name,
                            fontSize = 10.sp,
                            fontFamily = FontFamily(
                                Font(
                                    resId = com.example.uikit.R.font.poppins_regular
                                )
                            ),
                            fontWeight = FontWeight(400),
                            modifier = Modifier
                                .padding(start = 2.dp)
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                ) {
                    item {
                        cardList.forEach {
                            Card(
                                title = it.title,
                                text = it.text,
                                onCLick = it.onCLick,
                                image = it.image,
                                modifier = it.modifier
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(120.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                BottomBar(
                    currentScreen = 0,
                    onStatisticsClick = { },
                    onDiscoverClick = { },
                    onChatClick = { },
                    onProfileClick = { },
                    onCalendarClick = { navController.navigate(Navigation.ScheduleGame)}
                )
            }
        }
    } else {
        val rowList = listOf(
            RowData(
                icon = com.example.uikit.R.drawable.profile_icon,
                text = "My Profile",
                onClick = {},
                modifier = Modifier
                    .padding(top = 53.dp)
            ),
            RowData(
                icon = com.example.uikit.R.drawable.calendar_icon,
                text = "Schedule",
                onClick = {navController.navigate(Navigation.ScheduleGame)},
                modifier = Modifier
                    .padding(top = 34.dp)
            ),
            RowData(
                icon = com.example.uikit.R.drawable.statistics_icon,
                text = "Statistics",
                onClick = {},
                modifier = Modifier
                    .padding(top = 34.dp)
            ),
            RowData(
                icon = com.example.uikit.R.drawable.discover_icon,
                text = "Discover Combat",
                onClick = {},
                modifier = Modifier
                    .padding(top = 34.dp)
            ),
            RowData(
                icon = com.example.uikit.R.drawable.chat_icon,
                text = "Chat",
                onClick = {},
                modifier = Modifier
                    .padding(top = 34.dp)
            ),
            RowData(
                icon = R.drawable.change_language_icon,
                text = "Change Language",
                onClick = {},
                modifier = Modifier
                    .padding(top = 34.dp)
            ),
            RowData(
                icon = R.drawable.change_skin_icon,
                text = "Change App Skin",
                onClick = {},
                modifier = Modifier
                    .padding(top = 34.dp)
            )
        )
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
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(start = 60.dp)
                        .padding(end = 44.dp)
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            vm.onEvent(LandingEvent.ShowSideMenu)
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.close_icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 21.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.avatar_icon),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(56.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 11.dp)
                        ) {
                            Text(
                                text = state.name,
                                fontFamily = FontFamily(
                                    Font(
                                        resId = com.example.uikit.R.font.poppins_bold
                                    )
                                ),
                                fontWeight = FontWeight(700),
                                fontSize = 16.sp,
                                color = Color(0xFFFA5075)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(R.drawable.echelon_icon),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                                Text(
                                    text = "Gold Player",
                                    color = Color(0xFFF4C73E),
                                    fontSize = 10.sp,
                                    fontFamily = FontFamily(
                                        Font(
                                            resId = com.example.uikit.R.font.poppins_regular
                                        )
                                    ),
                                    fontWeight = FontWeight(400),
                                    modifier = Modifier
                                        .padding(start = 3.dp)
                                )
                            }
                        }
                    }
                    rowList.forEach {
                        Row(
                            modifier = it.modifier
                                .clickable {
                                    it.onClick
                                },
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(it.icon),
                                contentDescription = null,
                                tint = Color(0xFFF22E63),
                                modifier = Modifier
                                    .size(17.dp)
                            )
                            Text(text = it.text,
                                color = Black,
                                style = Theme.typography.headlineRegular,
                                modifier = Modifier
                                    .padding(start = 24.dp))
                        }
                    }
                }
            }
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter){
                Row(modifier = Modifier
                    .padding(bottom = 46.dp)
                    .clickable{
                        vm.onEvent(LandingEvent.Logout)
                    },
                    verticalAlignment = Alignment.CenterVertically){
                    Icon(painter = painterResource(R.drawable.logout_icon),
                        contentDescription = null,
                        tint = Color.Unspecified)
                    Text(text = "Logout",
                        color = Black,
                        style = Theme.typography.headlineRegular,
                        modifier = Modifier
                            .padding(start = 18.dp))
                }
            }
        }
    }
}