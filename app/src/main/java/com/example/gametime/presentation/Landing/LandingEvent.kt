package com.example.gametime.presentation.Landing

//23.03.2026
//Алексей
//класс событий для экрана landing
sealed class LandingEvent {
    data object ShowSideMenu: LandingEvent()
    data object Logout: LandingEvent()
}