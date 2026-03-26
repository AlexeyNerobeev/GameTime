package com.example.gametime.domain.repository

//25.03.2026
//Алексей
//репозиторий для работы с shared preferences
interface SharedPrefsRepository {
    fun saveOnBoardStatus(complete: Boolean)
    fun loadOnBoardStatus(): Boolean
    fun saveCurrentSession(userId: String)
    fun loadCurrentSession(): String
    fun clearCurrentSession()
}