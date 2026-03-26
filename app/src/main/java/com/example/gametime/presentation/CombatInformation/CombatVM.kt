package com.example.gametime.presentation.CombatInformation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.usecase.GetGameInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//26.03.2026
//Алексей
//viewmodel для экрана CombatInformation. Принимает usecase для получения информации об игре
@HiltViewModel
class CombatVM @Inject constructor(
    private val getGameInfoUseCase: GetGameInfoUseCase
): ViewModel() {
    private val _state = mutableStateOf(CombatState())
    val state: State<CombatState> = _state

    fun onEvent(event: CombatEvent){
        when(event){
            is CombatEvent.GetInfo -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val gameInfo = getGameInfoUseCase.invoke(event.value)
                    _state.value = state.value.copy(
                        combatName = gameInfo.name,
                        status = gameInfo.status,
                        price = gameInfo.winingPrice,
                        description = gameInfo.description,
                        category = gameInfo.category,
                        dateFrom = gameInfo.dateStart,
                        dateTo = gameInfo.dateEnd
                    )
                }
            }

            CombatEvent.ChangeNotification -> {
                _state.value = state.value.copy(
                    notification = !state.value.notification
                )
            }
        }
    }
}