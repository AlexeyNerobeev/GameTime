package com.example.gametime.presentation.DiscoverCombats

//25.03.2026
//Алексей
//класс событий для экрана discoverCombats
sealed class DiscoverEvent {
    data class EnteredSearch(val value: String): DiscoverEvent()
}