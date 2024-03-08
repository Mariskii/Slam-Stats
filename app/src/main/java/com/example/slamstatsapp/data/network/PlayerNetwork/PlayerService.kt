package com.example.slamstatsapp.data.network.PlayerNetwork

import android.util.Log
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.data.model.TeamModels.TeamPlayerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.RuntimeException
import javax.inject.Inject

class PlayerService @Inject constructor(private val api: PlayerApiClient)
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

    suspend fun getPlayerById(idPlayer: Int): PlayerModel?
    {
        return withContext(Dispatchers.IO){

            try
            {
                val playerResponse:Response<PlayerModel> = api.getPlayerById(idPlayer)

                if(playerResponse.isSuccessful)
                {
                    playerResponse.body()
                }
                else
                {
                    throw RuntimeException("Unsuccessful response: ${playerResponse.code()}")
                }
            }
            catch (e:Exception)
            {
                null
            }

        }
    }

    suspend fun getPlayerTeamns(idPlayer:Int):List<TeamPlayerModel>
    {
        return withContext(Dispatchers.IO){
            val teamsResponse:Response<List<TeamPlayerModel>> = api.getPlayerTeams(idPlayer)
            teamsResponse.body()?: emptyList()
        }
    }
}