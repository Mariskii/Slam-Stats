package com.example.slamstatsapp.data.network

import com.example.slamstatsapp.data.model.PlayerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PlayerService @Inject constructor(private val api:PlayerApiClient)
{

    suspend fun getPlayers():List<PlayerModel>
    {
        return withContext(Dispatchers.IO){
            val response:Response<List<PlayerModel>> = api.getAllPlayers()
            response.body() ?: emptyList()
        }
    }

    //Devolver todos los jugadores por nombre
    suspend fun getPlayersByName(namePlayer:String):List<PlayerModel>
    {
            //Dar el contexto a la corrutina, en este caso IO ya que es una peticion
        return withContext(Dispatchers.IO){
            val response:Response<List<PlayerModel>> = api.getPlayersByName(namePlayer)
            response.body() ?: emptyList()
        }
    }

    suspend fun getPlayerById(idPlayer: Int):PlayerModel
    {
        return withContext(Dispatchers.IO){
            val playerResponse:Response<PlayerModel> = api.getPlayerById(idPlayer)
            playerResponse.body() ?: PlayerModel(0,"1","1","1","1","2","2","","","")
        }
    }
}