package com.example.gametime.presentation.ScheduleGame

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//26.03.2026
//Алексей
//viewodel для экрана ScheduleGame
@HiltViewModel
class ScheduleVM @Inject constructor(

): ViewModel() {
    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    fun onEvent(event: ScheduleEvent){
        when(event){
            ScheduleEvent.ChangeNotification -> {
                _state.value = state.value.copy(
                    notification = !state.value.notification
                )
            }
            is ScheduleEvent.EnteredCategory -> {
                _state.value = state.value.copy(
                    category = event.value
                )
            }
            is ScheduleEvent.EnteredDateFrom -> {
                _state.value = state.value.copy(
                    dateFrom = event.value
                )
            }
            is ScheduleEvent.EnteredDateTo -> {
                _state.value = state.value.copy(
                    dateTo = event.value
                )
            }
            is ScheduleEvent.EnteredDescription -> {
                _state.value = state.value.copy(
                    description = event.value
                )
            }
            is ScheduleEvent.EnteredName -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }
            is ScheduleEvent.EnteredPrice -> {
                _state.value = state.value.copy(
                    winningPrice = event.value
                )
            }
            is ScheduleEvent.EnteredTimeForm -> {
                _state.value = state.value.copy(
                    timeFrom = event.value
                )
            }
            is ScheduleEvent.EnteredTimeTo -> {
                _state.value = state.value.copy(
                    timeTo = event.value
                )
            }
            ScheduleEvent.OnPublish -> {

            }
        }
    }
}