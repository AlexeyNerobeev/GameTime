package com.example.gametime.domain.usecase

import android.util.Patterns

//25.03.2026
//Алексей
//usecase для проверки валидности почты
class IsEmailValidUseCase {
    operator fun invoke(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}