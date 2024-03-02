package com.example.slamstatsapp.data.network.StatsNetwork

import com.example.slamstatsapp.data.model.StatsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StatsApiClient
{
    @GET("stats/player/{id}")
    suspend fun getPlayerStatsById(@Path("id") playerId:Int):Response<List<StatsModel>>
}