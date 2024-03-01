package com.example.slamstatsapp.domain

import com.example.slamstatsapp.data.TrophiesRepository
import com.example.slamstatsapp.data.model.TrophiesModel.TrophiesItemsModel
import com.example.slamstatsapp.data.model.TrophiesModel.TrophiesModel
import javax.inject.Inject

class GetTrophiesUseCase @Inject constructor(private val repository:TrophiesRepository)
{
    suspend fun getTrophiesByPlayerId(playerId:Int): TrophiesItemsModel
    {
        return repository.getTrophiesByPlayerId(playerId)
    }
}