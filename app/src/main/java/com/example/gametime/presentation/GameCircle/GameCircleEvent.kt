package com.example.gametime.presentation.GameCircle

//28.03.2026
//Алексей
//класс событий для экрана GameCircle
sealed class GameCircleEvent {
    data class StartGame(val width: Int, val height: Int): GameCircleEvent()
    data class OnCircleClick(val id: Int): GameCircleEvent()
}