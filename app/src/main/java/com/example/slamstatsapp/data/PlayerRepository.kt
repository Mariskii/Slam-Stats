package com.example.slamstatsapp.data

import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.data.model.PlayerProvider
import com.example.slamstatsapp.data.model.TeamModels.TeamPlayerModel
import com.example.slamstatsapp.data.network.PlayerNetwork.PlayerService
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val api: PlayerService)
{
    suspend fun getAllPlayers():List<PlayerModel>
    {
        val response:List<PlayerModel> = api.getPlayers()
        return response
    }

    suspend fun getPlayersByName(namePlayer: String): List<PlayerModel> {return api.getPlayersByName(namePlayer)}

    suspend fun getPlayerById(idPlayer:Int):PlayerModel?{return api.getPlayerById(idPlayer)}

    suspend fun getPlayerTeams(idPlayer: Int):List<TeamPlayerModel>{return api.getPlayerTeamns(idPlayer)}
}