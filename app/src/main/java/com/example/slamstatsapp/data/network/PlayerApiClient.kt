package com.example.slamstatsapp.data.network

import com.example.slamstatsapp.data.model.PlayerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApiClient {
    @GET("players/all")
    suspend fun getAllPlayers():Response<List<PlayerModel>>

    @GET("players")
    suspend fun getPlayersByName(@Query("nombre") name:String):Response<List<PlayerModel>>

    @GET("players/{id}")
    suspend fun getPlayerById(@Path("id") playerId:Int):Response<PlayerModel>
}