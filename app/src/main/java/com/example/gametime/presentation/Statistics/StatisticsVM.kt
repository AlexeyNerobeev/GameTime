package com.example.gametime.presentation.Statistics

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.network.domain.usecase.GetGameInfoUseCase
import com.example.network.domain.usecase.GetLastWeekCreatedGamesUseCase
import com.example.network.domain.usecase.GetUserStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

//27.03.2026
//Алексей
//viewmodel для экрана Statistics. Принимает usecase для получения статистики пользователя, id пользователя,
//получения данных об игре, получения созданных игр за последнюю неделю
@HiltViewModel
class StatisticsVM @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val getUserStatisticsUseCase: GetUserStatisticsUseCase,
    private val getGameInfoUseCase: GetGameInfoUseCase,
    private val getLastWeekCreatedGamesUseCase: GetLastWeekCreatedGamesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(StatisticsState())
    val state: State<StatisticsState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userID = loadCurrentUserUseCase.invoke()
            val statistics = getUserStatisticsUseCase.invoke(userID)
            val lastWeekGames = getLastWeekCreatedGamesUseCase.invoke(userID)

            val gamesByDay = lastWeekGames.groupBy {
                LocalDateTime.parse(it.createdAt)
                    .atZone(ZoneId.systemDefault())
                    .dayOfWeek
            }

            val daysOrder = listOf(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY
            )

            val counts = daysOrder.map { day ->
                gamesByDay[day]?.size ?: 0
            }

            val max = counts.maxOrNull()?.takeIf { it > 0 } ?: 1

            val normalized = counts.map {
                it.toFloat() / max
            }
            var earnings = 0
            var winsInImage = 0
            var winsInCircle = 0
            var totalWins = 0
            statistics.forEach {
                if (it.result == "win") {
                    earnings += it.totalWinning
                    totalWins++
                    val gameCategory = getGameInfoUseCase.invoke(it.gameId).category
                    if(gameCategory == "Circle"){
                        winsInCircle++
                    } else{
                        winsInImage++
                    }
                }
            }
            val totalGames = statistics.size
            val winsInCirclePercent = (winsInCircle.toFloat() / totalGames)
            val winsInImagePercent = (winsInImage.toFloat() / totalGames)
            val totalWinsPercent = (totalWins.toFloat() / totalGames * 100)
            _state.value = state.value.copy(
                earnings = earnings,
                winsInImagePercent = winsInImagePercent,
                winsInCirclePercent = winsInCirclePercent,
                totalWinsPercent = totalWinsPercent,
                lastWeekGames = normalized
            )
        }
    }
}