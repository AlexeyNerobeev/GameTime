package com.example.gametime.domain.usecase

import com.example.gametime.domain.repository.SharedPrefsRepository

//25.03.2026
//Алексей
//usecase для сохранения статуса прохождения onBoard
class SaveOnBoardStatusUseCase(private val sharedPrefsRepository: SharedPrefsRepository) {
    operator fun invoke(status: Boolean){
        sharedPrefsRepository.saveOnBoardStatus(status)
    }
}