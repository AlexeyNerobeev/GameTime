package com.example.gametime.presentation.OnBoard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardVM @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(OnBoardState())
    val state: State<OnBoardState> = _state

    fun onEvent(event: OnBoardEvent){
        when(event){
            OnBoardEvent.GoToRegistration -> {

            }
            OnBoardEvent.NextOnBoard -> {
                val next = state.value.currentOnBoard + 1
                _state.value = state.value.copy(
                    currentOnBoard = next
                )
            }
        }
    }
}