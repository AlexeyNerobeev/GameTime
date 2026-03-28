package com.example.gametime.presentation.GameCircle

//28.03.2026
//Алексей
//класс событий для экрана GameCircle
sealed class GameCircleEvent {
    data class InitGame(val centerX: Float, val centerY: Float) : GameCircleEvent()
    data class CircleClicked(val circleId: Int) : GameCircleEvent()
    data object Surrender : GameCircleEvent()
    data class GetGameInfo(val id: Int, val winningPrice: Int): GameCircleEvent()
}