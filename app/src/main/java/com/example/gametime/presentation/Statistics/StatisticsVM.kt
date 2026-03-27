package com.example.gametime.presentation.Statistics

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gametime.domain.usecase.LoadCurrentUserUseCase
import com.example.network.domain.usecase.GetGameInfoUseCase
import com.example.network.domain.usecase.GetUserStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//27.03.2026
//Алексей
//viewmodel для экрана Statistics. Принимает usecase для получения статистики пользователя, id пользователя,
//получения данных об игре
@HiltViewModel
class StatisticsVM @Inject constructor(
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase,
    private val getUserStatisticsUseCase: GetUserStatisticsUseCase,
    private val getGameInfoUseCase: GetGameInfoUseCase
) : ViewModel() {
    private val _state = mutableStateOf(StatisticsState())
    val state: State<StatisticsState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val userID = loadCurrentUserUseCase.invoke()
            val statistics = getUserStatisticsUseCase.invoke(userID)
            var earnings = 0
            var winsInImage = 0
            var winsInCircle = 0
            var totalWins = 0
            statistics.forEach {
                if (it.result == "win") {
                    earnings + it.totalWinning
                    totalWins++
                    val gameCategory = getGameInfoUseCase.invoke(it.gameId).category
                    if(gameCategory == "circle"){
                        winsInCircle++
                    } else{
                        winsInImage++
                    }
                }
            }
            val totalGames = statistics.size
            val winsInCirclePercent = (winsInCircle / totalGames * 100).toFloat()
            val winsInImagePercent = (winsInImage / totalGames * 100).toFloat()
            val totalWinsPercent = (totalGames / totalWins * 100).toFloat()
            Log.d("qwe", winsInCirclePercent.toString())
            _state.value = state.value.copy(
                earnings = earnings,
                winsInImagePercent = winsInImagePercent,
                winsInCirclePercent = winsInCirclePercent,
                totalWinsPercent = totalWinsPercent
            )
        }
    }
}