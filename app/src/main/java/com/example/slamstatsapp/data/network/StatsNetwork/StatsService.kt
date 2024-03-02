package com.example.slamstatsapp.data.network.StatsNetwork

import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.data.model.StatsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class StatsService @Inject constructor(private val api:StatsApiClient)
{
    suspend fun getPlayerStatsById(playerId:Int):List<StatsModel>
    {
        return withContext(Dispatchers.IO){
             api.getPlayerStatsById(playerId).body() ?: emptyList()
        }
    }
}