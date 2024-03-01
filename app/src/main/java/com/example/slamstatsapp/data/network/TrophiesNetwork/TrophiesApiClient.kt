package com.example.slamstatsapp.data.network.TrophiesNetwork

import com.example.slamstatsapp.data.model.TrophiesModel.TrophiesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TrophiesApiClient
{
    @GET("trophies/players/{id}")
    suspend fun getTrophiesByPlayerId(@Path("id") playerId: Int):Response<TrophiesModel>
}