package com.example.gametime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gametime.presentation.Landing.LandingScreen
import com.example.gametime.presentation.OnBoard.OnBoardScreen
import com.example.gametime.presentation.Registration.RegistrationScreen
import com.example.gametime.presentation.ScheduleGame.ScheduleGameScreen
import com.example.gametime.presentation.SignIn.SignInScreen
import com.example.gametime.presentation.Splash.SplashScreen
import com.example.gametime.ui.theme.GameTimeTheme
import dagger.hilt.android.AndroidEntryPoint

//25.03.2026
//Алексей
//старт MainActivity
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameTimeTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Navigation.Splash){
                    composable<Navigation.Splash> {
                        SplashScreen(navController)
                    }
                    composable<Navigation.OnBoard> {
                        OnBoardScreen(navController)
                    }
                    composable<Navigation.Registration> {
                        RegistrationScreen(navController)
                    }
                    composable<Navigation.SignIn> {
                        SignInScreen(navController)
                    }
                    composable<Navigation.Landing> {
                        LandingScreen(navController)
                    }
                    composable<Navigation.ScheduleGame> {
                        ScheduleGameScreen(navController)
                    }
                }
            }
        }
    }
}