package com.example.gametime.domain.usecase

import com.example.gametime.domain.repository.SharedPrefsRepository

//25.03.2026
//Алексей
//usecase для сохранения id текущего пользователя
class SaveCurrentUserUseCase(private val sharedPrefsRepository: SharedPrefsRepository) {
    operator fun invoke(userId: String){
        sharedPrefsRepository.saveCurrentSession(userId)
    }
}