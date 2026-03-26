package com.example.gametime.presentation.CombatInformation

//26.03.2026
//Алексей
//класс событий для экрана combatInformation
sealed class CombatEvent {
    data class GetInfo(val value: Int): CombatEvent()
    data object ChangeNotification: CombatEvent()
}