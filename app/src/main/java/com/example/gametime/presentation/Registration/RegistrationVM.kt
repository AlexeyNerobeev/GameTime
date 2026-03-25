package com.example.gametime.presentation.Registration

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.IsEmailValidUseCase
import com.example.gametime.domain.usecase.SaveCurrentUserUseCase
import com.example.network.domain.model.User
import com.example.network.domain.usecase.CreateProfileUseCase
import com.example.network.domain.usecase.RegistrationUseCase
import com.example.network.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//25.03.2026
//Алексей
//viewmodel для экрана регистрации. Принимает usecase для регистрации, создания профиля,
// проверку email на валидность, сохранение id текущего пользователя
@HiltViewModel
class RegistrationVM @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val createProfileUseCase: CreateProfileUseCase,
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val saveCurrentUserUseCase: SaveCurrentUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(RegistrationState())
    val state: State<RegistrationState> = _state

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            RegistrationEvent.CreateAccount -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (state.value.fullName.isNotEmpty() &&
                        state.value.userName.isNotEmpty() &&
                        state.value.phone.isNotEmpty() &&
                        isEmailValidUseCase.invoke(state.value.email) &&
                        state.value.password.isNotEmpty() &&
                        state.value.confirmPassword.isNotEmpty() &&
                        state.value.password == state.value.confirmPassword
                    ) {
                        val userId = registrationUseCase.invoke(
                            email = state.value.email,
                            password = state.value.password
                        )
                        if (userId.isNotEmpty()) {
                            saveCurrentUserUseCase.invoke(userId)
                            createProfileUseCase.invoke(
                                User(
                                    fullName = state.value.fullName,
                                    userName = state.value.userName,
                                    phone = state.value.phone,
                                    email = state.value.email,
                                    userId = userId
                                )
                            )
                            _state.value = state.value.copy(
                                isComplete = true
                            )
                        }
                    }
                }
            }

            is RegistrationEvent.EnteredConfirmPassword -> {
                _state.value = state.value.copy(
                    confirmPassword = event.value
                )
            }

            is RegistrationEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }

            is RegistrationEvent.EnteredFullName -> {
                _state.value = state.value.copy(
                    fullName = event.value
                )
            }

            is RegistrationEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }

            is RegistrationEvent.EnteredPhone -> {
                _state.value = state.value.copy(
                    phone = event.value
                )
            }

            is RegistrationEvent.EnteredUserName -> {
                _state.value = state.value.copy(
                    userName = event.value
                )
            }
        }
    }
}