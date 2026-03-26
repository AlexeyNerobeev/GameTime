package com.example.gametime.presentation.OnBoard

//25.03.2026
//Алексей
//класс событий для экрана onBoard
sealed class OnBoardEvent {
    data object NextOnBoard: OnBoardEvent()
    data object GoToRegistration: OnBoardEvent()
}