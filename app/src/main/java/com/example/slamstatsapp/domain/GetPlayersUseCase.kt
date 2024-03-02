package com.example.slamstatsapp.domain

import com.example.slamstatsapp.data.PlayerRepository
import com.example.slamstatsapp.data.model.PlayerModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

class GetPlayersUseCase @Inject constructor(private val repository:PlayerRepository)
{
    suspend fun getAllPlayers(): List<PlayerModel> = repository.getAllPlayers()
    suspend fun getPlayersByName(playerName:String): List<PlayerModel> = repository.getPlayersByName(playerName)
    suspend fun getPlayersById(playerId:Int): PlayerModel = repository.getPlayerById(playerId)
    suspend fun getRandomPlayerById():PlayerModel
    {
        val num = Random.nextInt(1, 21)
        return repository.getPlayerById(num)
    }
}