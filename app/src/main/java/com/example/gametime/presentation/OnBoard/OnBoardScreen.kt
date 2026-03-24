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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.R
import com.example.uikit.ProgressCircles
import com.example.uikit.Theme

@Composable
fun OnBoardScreen(navController: NavController, vm: OnBoardVM = hiltViewModel()) {
    val state = vm.state.value
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {



                Text(text = "Skip",
                    style = Theme.typography.captionSemibold,
                    color = Color(0xFFFA5075),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable{

                        })

                ProgressCircles(
                    currentNumber = 1
                )
            }
        }
    }
}