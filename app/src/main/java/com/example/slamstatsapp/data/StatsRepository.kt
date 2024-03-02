package com.example.slamstatsapp.data

import com.example.slamstatsapp.data.model.StatsModel
import com.example.slamstatsapp.data.network.StatsNetwork.StatsService
import javax.inject.Inject

class StatsRepository @Inject constructor(private val api:StatsService)
{
    suspend fun getStatsByPlayerId(playerId:Int):List<StatsModel>
    {
        return api.getPlayerStatsById(playerId)
    }
}