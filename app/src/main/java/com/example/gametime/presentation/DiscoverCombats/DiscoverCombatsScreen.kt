package com.example.gametime.presentation.DiscoverCombats

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.Navigation
import com.example.gametime.R
import com.example.gametime.common.DiscoverRow
import com.example.gametime.common.GameBox
import com.example.gametime.common.UserCard
import com.example.uikit.Black
import com.example.uikit.BottomBar
import com.example.uikit.Theme

//25.03.2026
//Алексей
//метод для отображения экрана discoverCombats. Принимает контроллер навигации и viewmodel
@Composable
fun DiscoverCombatsScreen(navController: NavController, vm: DiscoverVM = hiltViewModel()) {
    val state = vm.state.value
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFF6480),
                                Color(0xFFF22E63)
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 30.dp)
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_icon),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 36.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Discover \n" +
                                    "Combats",
                            fontWeight = FontWeight(700),
                            fontFamily = FontFamily(
                                Font(
                                    resId = com.example.uikit.R.font.poppins_bold
                                )
                            ),
                            fontSize = 22.sp,
                            color = Color.White
                        )
                        Text(
                            text = "FILTER",
                            style = Theme.typography.caption2Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                    }
                    OutlinedTextField(
                        value = state.search,
                        onValueChange = {
                            vm.onEvent(DiscoverEvent.EnteredSearch(it))
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Black,
                            unfocusedTextColor = Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedPlaceholderColor = Color(0xFFC9C9C9),
                            focusedBorderColor = Color(0xFFFA5075),
                            unfocusedBorderColor = Color(0xFFFA5075)
                        ),
                        shape = RoundedCornerShape(100.dp),
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .padding(bottom = 29.dp)
                            .fillMaxWidth()
                            .heightIn(41.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.search_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        },
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Search Combat",
                                style = Theme.typography.caption2Regular
                            )
                        }
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
            ) {
                item {
                    DiscoverRow(
                        text = "Trending this week"
                    )
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )
                }
                items(state.gamesList) {
                    GameBox(
                        name = it.name,
                        status = it.status,
                        price = it.winingPrice.toString(),
                        modifier = Modifier
                            .clickable{
                                navController.navigate(Navigation.CombatInfo(it.id))
                            }
                    )
                }
                item {
                    DiscoverRow(
                        text = "Most Popular Players",
                        modifier = Modifier
                            .padding(top = 41.dp)
                    )
                    Row(modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        UserCard(
                            name = "Scott Brown",
                            crown = "Gold Player",
                            status = "Online",
                            category = "Action, Soccer...",
                            modifier = Modifier
                                .clickable{
                                    navController.navigate(Navigation.PlayerInfo)
                                }
                        )
                        UserCard(
                            name = "Teslar fullar",
                            crown = "Silver Player",
                            status = "Away",
                            category = "Action, Soccer...",
                            modifier = Modifier
                                .clickable{
                                    navController.navigate(Navigation.PlayerInfo)
                                }
                        )
                    }
                    DiscoverRow(
                        modifier = Modifier
                            .padding(top = 34.dp)
                            .padding(bottom = 20.dp),
                        text = "Latest Combats"
                    )
                }
                items(state.gamesList) {
                    GameBox(
                        name = it.name,
                        status = it.status,
                        price = it.winingPrice.toString(),
                        modifier = Modifier
                            .clickable{
                                navController.navigate(Navigation.CombatInfo(it.id))
                            }
                    )
                }
                item {
                    Spacer(modifier = Modifier
                        .height(110.dp))
                }
            }
        }
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter){
            BottomBar(
                currentScreen = 2,
                onStatisticsClick = { navController.navigate(Navigation.Statistics) },
                onDiscoverClick = {  },
                onChatClick = {  },
                onProfileClick = {  },
                onCalendarClick = { navController.navigate(Navigation.Landing) }
            )
        }
    }
}