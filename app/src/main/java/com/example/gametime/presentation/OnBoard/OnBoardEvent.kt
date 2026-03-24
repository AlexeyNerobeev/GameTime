package com.example.gametime.presentation.OnBoard

sealed class OnBoardEvent {
    data object NextOnBoard: OnBoardEvent()
    data object GoToRegistration: OnBoardEvent()
}