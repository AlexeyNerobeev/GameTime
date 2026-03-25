package com.example.gametime.presentation.Landing

//23.03.2026
//Алексей
//класс состояний для экрана landing
data class LandingState(
    val name: String = "",
    val showSideMenu: Boolean = false,
    val isLogout: Boolean = false
)