package com.example.slamstatsapp.data.network.TrophiesNetwork

import com.example.slamstatsapp.data.model.TrophiesModel.TrophiesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class TrophiesService @Inject constructor(private val api: TrophiesApiClient)
{
    suspend fun getTrophiesByPlayerId(playerId:Int): TrophiesModel?
    {
        return withContext(Dispatchers.IO){
            val response:Response<TrophiesModel> = api.getTrophiesByPlayerId(playerId)
            response.body()
        }
    }
}