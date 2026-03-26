package com.example.gametime.presentation.CombatInformation

//26.03.2026
//Алексей
//класс состояний для экрана CombatInformation
data class CombatState(
    val combatName: String = "",
    val status: String = "",
    val price: Int = 0,
    val description: String = "",
    val category: String = "",
    val dateFrom: String = "",
    val dateTo: String = "",
    val notification: Boolean = true
)