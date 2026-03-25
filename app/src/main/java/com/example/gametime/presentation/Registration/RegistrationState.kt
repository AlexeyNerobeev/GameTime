package com.example.gametime.presentation.Registration

//25.03.2026
//Алексей
//класс состояний для экрана регистрации
data class RegistrationState(
    val fullName: String = "",
    val userName: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isComplete: Boolean = false
)