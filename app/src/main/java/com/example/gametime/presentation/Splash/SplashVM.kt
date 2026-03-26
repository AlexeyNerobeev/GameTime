package com.example.gametime.presentation.Splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.gametime.domain.usecase.LoadOnBoardStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//25.03.2026
//Алексей
//viewmodel для экрана splash. Принимает usecase для получения id текущего пользователя и статуса прохождения onBoard
@HiltViewModel
class SplashVM @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val loadOnBoardStatusUseCase: LoadOnBoardStatusUseCase
): ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    init {
        val onBoardStatus = loadOnBoardStatusUseCase.invoke()
        val userId = loadCurrentUserUseCase.invoke()
        val isRegistered = userId.isNotEmpty()
        _state.value = state.value.copy(
            onBoardComplete = onBoardStatus,
            isRegistered = isRegistered
        )
    }
}