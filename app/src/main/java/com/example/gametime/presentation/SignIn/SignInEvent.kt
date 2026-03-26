package com.example.gametime.presentation.SignIn

//25.03.2026
//Алексей
//класс событий для экрана авторизации
sealed class SignInEvent {
    data class EnteredEmail(val value: String): SignInEvent()
    data class EnteredPassword(val value: String): SignInEvent()
    data object SignIn: SignInEvent()
}