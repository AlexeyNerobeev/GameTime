package com.example.gametime.presentation.DiscoverCombats

import com.example.network.domain.model.Game

//25.03.2026
//Алексей
//класс состояний для экрана discoverCombats
data class DiscoverState(
    val search: String = "",
    val gamesList: List<Game> = listOf()
)