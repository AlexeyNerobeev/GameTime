package com.example.gametime.presentation.Landing

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.ClearCurrentSessionUseCase
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.network.domain.usecase.GetProfileUseCase
import com.example.network.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//23.03.2026
//Алексей
//viewmodel для экрана landing. Принимает usecase для получения профиля пользователя,
// получения id текущего пользоваетля, выхода из профиля, чистки id текущего пользователя
@HiltViewModel
class LandingVM @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val clearCurrentSessionUseCase: ClearCurrentSessionUseCase
): ViewModel() {
    private val _state = mutableStateOf(LandingState())
    val state: State<LandingState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = loadCurrentUserUseCase.invoke()
            val profile = getProfileUseCase.invoke(userId)
            _state.value = state.value.copy(
                name = profile.userName
            )
        }
    }

    fun onEvent(event: LandingEvent){
        when(event){
            LandingEvent.ShowSideMenu -> {
                _state.value = state.value.copy(
                    showSideMenu = !state.value.showSideMenu
                )
            }

            LandingEvent.Logout -> {
                viewModelScope.launch(Dispatchers.IO) {
                    logoutUseCase.invoke()
                    clearCurrentSessionUseCase.invoke()
                    _state.value = state.value.copy(
                        isLogout = true
                    )
                }
            }
        }
    }
}