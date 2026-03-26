package com.example.gametime.presentation.SignIn

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.SaveCurrentUserUseCase
import com.example.network.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//25.03.2026
//Алексей
//viewmodel для экрана авторизации. Принимает usecase для авторизации, сохранения id текущего пользователя
@HiltViewModel
class SignInVM @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val saveCurrentUserUseCase: SaveCurrentUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }

            is SignInEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }

            SignInEvent.SignIn -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (state.value.email.isNotEmpty() &&
                        state.value.password.isNotEmpty()
                    ) {
                        val userId = signInUseCase.invoke(state.value.email,
                            state.value.password)
                        if(userId.isNotEmpty()){
                            saveCurrentUserUseCase.invoke(userId)
                            _state.value = state.value.copy(
                                isComplete = true
                            )
                        }
                    }
                }
            }
        }
    }
}