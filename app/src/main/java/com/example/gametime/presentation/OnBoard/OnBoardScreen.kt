package com.example.gametime.presentation.OnBoard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.Navigation
import com.example.gametime.R
import com.example.uikit.Buttons.MainButton
import com.example.uikit.ProgressCircles
import com.example.uikit.Theme

//25.03.2026
//Алексей
//метод отображения экрана onBoard. Принимает контроллер навигации и viewmodel
@Composable
fun OnBoardScreen(navController: NavController, vm: OnBoardVM = hiltViewModel()) {
    val state = vm.state.value
    LaunchedEffect(key1 = state.complete) {
        if(state.complete){
            navController.navigate(Navigation.Registration){
                popUpTo(0)
            }
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
                .clickable {
                    vm.onEvent(OnBoardEvent.NextOnBoard)
                },
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 45.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = when (state.currentOnBoard) {
                        1 -> painterResource(R.drawable.onb1_image)
                        2 -> painterResource(R.drawable.onb2_image)
                        3 -> painterResource(R.drawable.onb3_image)
                        else -> painterResource(R.drawable.onb1_image)
                    },
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = when(state.currentOnBoard){
                            1 -> 48.dp
                            2 -> 27.dp
                            3 -> 30.dp
                            else -> 0.dp
                        })
                        .padding(
                            bottom = if (state.currentOnBoard == 3) {
                                0.dp
                            } else {
                                35.dp
                            }
                        )
                        .padding(
                            end = if (state.currentOnBoard == 3) {
                                29.dp
                            } else {
                                0.dp
                            }
                        )
                )
                Text(
                    text = when (state.currentOnBoard) {
                        1 -> "Get Paid! Playing\nVideo Game"
                        2 -> "Schedule Games\nWith Friends"
                        3 -> "Text, Audio and\nVideo Chat"
                        else -> ""
                    },
                    textAlign = TextAlign.Center,
                    style = Theme.typography.title1Extrabold,
                    color = Color(0xFFFA5075)
                )
                Text(
                    text = when (state.currentOnBoard) {
                        1 -> "Earn points and real cash when\nyou win a battle with no delay in\ncashing out"
                        2 -> "Easily create an upcoming\nevent and get ready for battle.\nYeah! real combat fella."
                        3 -> "Intuitive real life experience on\nmobile. Chat with fellow gamers\nbefore and after combat for\nfree!"
                        else -> ""
                    },
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    style = Theme.typography.captionRegular,
                    modifier = Modifier
                        .padding(top = 36.dp)
                )
                if (state.currentOnBoard != 3) {
                    Text(
                        text = "Skip",
                        style = Theme.typography.captionSemibold,
                        color = Color(0xFFFA5075),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .padding(vertical = 63.dp)
                            .clickable {
                                vm.onEvent(OnBoardEvent.GoToRegistration)
                            })
                } else {
                    MainButton(
                        text = "Let’s Combat!",
                        modifier = Modifier
                            .padding(vertical = 38.dp),
                        onCLick = {
                            vm.onEvent(OnBoardEvent.GoToRegistration)
                        }
                    )
                }
                ProgressCircles(
                    currentNumber = state.currentOnBoard
                )
            }
        }
    }
}