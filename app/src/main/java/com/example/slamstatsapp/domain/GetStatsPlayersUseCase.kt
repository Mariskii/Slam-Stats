package com.example.slamstatsapp.domain

import com.example.slamstatsapp.data.StatsRepository
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.data.model.StatsModel
import javax.inject.Inject

class GetStatsPlayersUseCase @Inject constructor(private val repository: StatsRepository)
{
    suspend fun getPlayerStatsById(playerId:Int): List<StatsModel> = repository.getStatsByPlayerId(playerId)
}