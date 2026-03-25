package com.example.gametime.data.repositoryImpl

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.gametime.domain.repository.SharedPrefsRepository

//25.03.2026
//Алексей
//реализация репозитория для работы с shared preferences
class SharedPrefsRepositoryImpl(context: Context): SharedPrefsRepository {
    private val sharedPrefs = context.getSharedPreferences("Data", Context.MODE_PRIVATE)

    //метод для сохранения статуса прохождения onBoard. Принимает статус прохождения
    override fun saveOnBoardStatus(complete: Boolean) {
        try {
            sharedPrefs.edit { putBoolean("onBoard", complete) }
        } catch (e: Exception) {
            Log.e("saveOnBoardStatus", e.message.toString())
        }
    }

    //метод для получения статуса прохождения onBoard. Возвращает статус прохождения
    override fun loadOnBoardStatus(): Boolean {
        try {
            return sharedPrefs.getBoolean("onBoard", false)
        } catch (e: Exception) {
            Log.e("loadOnBoardStatus", e.message.toString())
            return false
        }
    }

    //метод для сохранения id текущего пользователя. Принимает id пользователя
    override fun saveCurrentSession(userId: String) {
        try {
            sharedPrefs.edit{ putString("userId", userId)}
        } catch (e: Exception) {
            Log.e("saveCurrentSession", e.message.toString())
        }
    }

    //метод для получения id текущего пользователя. Возвращает id пользователя
    override fun loadCurrentSession(): String {
        try {
            val userId = sharedPrefs.getString("userId", "")
            return userId ?: ""
        } catch (e: Exception) {
            Log.e("loadCurrentSession", e.message.toString())
            return ""
        }
    }

    //метод для очитски текущего пользователя
    override fun clearCurrentSession() {
        try {
            sharedPrefs.edit { putString("userId", "") }
        } catch (e: Exception) {
            Log.e("clearCurrentSession", e.message.toString())
        }
    }
}