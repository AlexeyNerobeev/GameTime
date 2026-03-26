package com.example.gametime.domain.usecase

import com.example.gametime.domain.repository.SharedPrefsRepository

//23.03.2026
//Алексей
//usecase для очистки текущего пользователя
class ClearCurrentSessionUseCase(private val sharedPrefsRepository: SharedPrefsRepository) {
    operator fun invoke(){
        sharedPrefsRepository.clearCurrentSession()
    }
}