package com.example.gametime

import kotlinx.serialization.Serializable

sealed class Navigation {
    @Serializable
    data object Splash: Navigation()
}