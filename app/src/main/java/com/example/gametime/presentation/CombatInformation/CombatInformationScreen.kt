package com.example.gametime.presentation.CombatInformation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.R

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
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.back_icon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(start = 30.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 36.dp)
                    .padding(horizontal = 40.dp)
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
                ){

                }
            }
        }
    }
}