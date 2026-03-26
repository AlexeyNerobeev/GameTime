package com.example.gametime

import kotlinx.serialization.Serializable
import java.io.Serial

//25.03.2026
//Алексей
//класс навигации
sealed class Navigation {
    @Serializable
    data object Splash: Navigation()

    @Serializable
    data object OnBoard: Navigation()

    @Serializable
    data object Registration: Navigation()

    @Serializable
    data object SignIn: Navigation()

    @Serializable
    data object Landing: Navigation()

    @Serializable
    data object ScheduleGame: Navigation()

    @Serializable
    data object DiscoverCombats: Navigation()

    @Serializable
    data class CombatInfo(val value: Int): Navigation()
}