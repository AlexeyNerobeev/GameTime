package com.example.gametime.domain.usecase

import com.example.gametime.domain.repository.SharedPrefsRepository

//25.03.2026
//Алексей
//usecase для получения статуса прохождения onBoard
class LoadOnBoardStatusUseCase(private val sharedPrefsRepository: SharedPrefsRepository) {
    operator fun invoke(): Boolean{
        return sharedPrefsRepository.loadOnBoardStatus()
    }
}