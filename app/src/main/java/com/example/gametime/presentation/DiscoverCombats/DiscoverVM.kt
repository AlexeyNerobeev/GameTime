package com.example.gametime.presentation.DiscoverCombats

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.usecase.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//25.03.2026
//Алексей
//viewmodel для экрана discoverCombats. Принимает usecase для получения списка всех игр
@HiltViewModel
class DiscoverVM @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
): ViewModel() {
    private val _state = mutableStateOf(DiscoverState())
    val state: State<DiscoverState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val gamesList = getGamesUseCase.invoke()
            _state.value = state.value.copy(
                gamesList = gamesList
            )
        }
    }

    fun onEvent(event: DiscoverEvent){
        when(event){
            is DiscoverEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    search = event.value
                )
            }
        }
    }
}