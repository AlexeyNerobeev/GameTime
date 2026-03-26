package com.example.gametime.domain.usecase

import com.example.gametime.domain.repository.SharedPrefsRepository

//25.03.2026
//Алексей
//usecase для получения id текущего пользователя
class LoadCurrentUserUseCase(private val sharedPrefsRepository: SharedPrefsRepository) {
    operator fun invoke(): String{
        return sharedPrefsRepository.loadCurrentSession()
    }
}