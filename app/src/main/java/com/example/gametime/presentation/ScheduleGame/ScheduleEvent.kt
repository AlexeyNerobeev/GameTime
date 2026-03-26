package com.example.gametime.presentation.ScheduleGame

//26.03.2026
//Алексей
//класс событий для экрана ScheduleEvent
sealed class ScheduleEvent {
    data class EnteredName(val value: String): ScheduleEvent()
    data class EnteredCategory(val value: String): ScheduleEvent()
    data class EnteredPrice(val value: String): ScheduleEvent()
    data class EnteredDateFrom(val value: String): ScheduleEvent()
    data class EnteredTimeForm(val value: String): ScheduleEvent()
    data class EnteredDateTo(val value: String): ScheduleEvent()
    data class EnteredTimeTo(val value: String): ScheduleEvent()
    data class EnteredDescription(val value: String): ScheduleEvent()
    data object ChangeNotification: ScheduleEvent()
    data object OnPublish: ScheduleEvent()
}