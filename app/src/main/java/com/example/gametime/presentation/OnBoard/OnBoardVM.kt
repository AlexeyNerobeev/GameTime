package com.example.gametime.presentation.OnBoard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gametime.domain.usecase.SaveOnBoardStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//25.03.2026
//Алексей
//viewmodel для экрана onBoard. Принимает usecase для сохранения состояния прохождения onBoard
@HiltViewModel
class OnBoardVM @Inject constructor(
    private val saveOnBoardStatusUseCase: SaveOnBoardStatusUseCase
): ViewModel() {
    private val _state = mutableStateOf(OnBoardState())
    val state: State<OnBoardState> = _state

    //метод для вызова событий. Принимает событие
    fun onEvent(event: OnBoardEvent){
        when(event){
            OnBoardEvent.GoToRegistration -> {
                saveOnBoardStatusUseCase.invoke(true)
                _state.value = state.value.copy(
                    complete = true
                )
            }
            OnBoardEvent.NextOnBoard -> {
                if(state.value.currentOnBoard < 3) {
                    val next = state.value.currentOnBoard + 1
                    _state.value = state.value.copy(
                        currentOnBoard = next
                    )
                }
            }
        }
    }
}