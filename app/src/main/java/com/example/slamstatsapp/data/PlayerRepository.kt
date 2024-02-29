package com.example.slamstatsapp.data

import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.data.model.PlayerProvider
import com.example.slamstatsapp.data.network.PlayerService
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val api:PlayerService, private val playerProvider: PlayerProvider)
{
    suspend fun getAllPlayers():List<PlayerModel>
    {
        val response:List<PlayerModel> = api.getPlayers()
        playerProvider.players = response
        return response
    }

    suspend fun getPlayersByName(namePlayer: String): List<PlayerModel> {return api.getPlayersByName(namePlayer)}

    suspend fun getPlayerById(idPlayer:Int):PlayerModel{return api.getPlayerById(idPlayer)}
}