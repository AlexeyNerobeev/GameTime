package com.example.gametime.presentation.ScheduleGame

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.network.domain.model.Game
import com.example.network.domain.usecase.CreateGameUseCase
import com.example.network.domain.usecase.GetLastWeekCreatedGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//26.03.2026
//Алексей
//viewodel для экрана ScheduleGame. Принимает usecase для создания новой игры,
//получения id текущего пользователя
@HiltViewModel
class ScheduleVM @Inject constructor(
    private val createGameUseCase: CreateGameUseCase,
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase
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
                if (state.value.name.isNotEmpty() &&
                    state.value.category.isNotEmpty() &&
                    state.value.winningPrice.isNotEmpty() &&
                    state.value.dateFrom.isNotEmpty() &&
                    state.value.timeFrom.isNotEmpty() &&
                    state.value.dateTo.isNotEmpty() &&
                    state.value.timeTo.isNotEmpty() &&
                    state.value.description.isNotEmpty()) {
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            val userId = loadCurrentUserUseCase.invoke()
                            createGameUseCase.invoke(
                                game = Game(
                                    name = state.value.name,
                                    category = state.value.category,
                                    winingPrice = state.value.winningPrice.toInt(),
                                    dateStart = state.value.dateFrom,
                                    dateEnd = state.value.dateTo,
                                    description = state.value.description,
                                    userId = userId,
                                    status = "Open"
                                )
                            )
                            _state.value = state.value.copy(
                                isComplete = true
                            )
                        } catch (e: Exception){
                            Log.e("OnPublish", e.message.toString())
                        }
                    }
                }
            }
        }
    }
}