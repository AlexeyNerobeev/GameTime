package com.example.gametime.presentation.Splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gametime.Navigation
import com.example.gametime.R
import com.example.uikit.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay

//25.03.2026
//Алексей
//метод для отображения стратового экрана. Принимает контроллер навигации, viewmodel
@Composable
fun SplashScreen(navController: NavController, vm: SplashVM = hiltViewModel()) {
    val state = vm.state.value
    LaunchedEffect(key1 = state.onBoardComplete, key2 = state.isRegistered) {
        delay(3000)
        if(state.onBoardComplete){
            navController.navigate(Navigation.SignIn)
        } else{
            navController.navigate(Navigation.OnBoard)
        }
        if(state.isRegistered){
            navController.navigate(Navigation.Landing)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF6480),
                        Color(0xFFF22E63)
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Icon(
                painter = painterResource(R.drawable.splash_logo_icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            Icon(
                painter = painterResource(R.drawable.graphics_icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
