package com.example.gametime.presentation.SignIn

//25.03.2026
//Алексей
//класс состояний для экрана авторизации
data class SignInState(
    val email: String = "",
    val password: String = "",
    val isComplete: Boolean = false
)