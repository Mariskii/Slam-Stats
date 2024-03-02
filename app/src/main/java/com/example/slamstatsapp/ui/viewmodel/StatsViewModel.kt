package com.example.slamstatsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slamstatsapp.data.model.StatsModel
import com.example.slamstatsapp.domain.GetStatsPlayersUseCase
import com.example.slamstatsapp.domain.GetTrophiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(private val getStatsUseCase: GetStatsPlayersUseCase) : ViewModel()
{
    //Estadísticas del jugador a buscar
    private var _playerStats = MutableStateFlow<List<StatsModel>>(emptyList())
    var playerStats:StateFlow<List<StatsModel>> = _playerStats

    fun searchPlayerStats(playerId:Int)
    {
        viewModelScope.launch {
            _playerStats.value = getStatsUseCase.getPlayerStatsById(playerId)
        }
    }
}