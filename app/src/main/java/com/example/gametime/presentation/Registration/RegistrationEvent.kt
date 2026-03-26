package com.example.gametime.presentation.Registration

//25.03.2026
//Алексей
//класс событий для экрана регистрации
sealed class RegistrationEvent {
    data class EnteredFullName(val value: String): RegistrationEvent()
    data class EnteredUserName(val value: String): RegistrationEvent()
    data class EnteredPhone(val value: String): RegistrationEvent()
    data class EnteredEmail(val value: String): RegistrationEvent()
    data class EnteredPassword(val value: String): RegistrationEvent()
    data class EnteredConfirmPassword(val value: String): RegistrationEvent()
    data object CreateAccount: RegistrationEvent()
}