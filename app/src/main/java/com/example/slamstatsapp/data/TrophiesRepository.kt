package com.example.slamstatsapp.data

import com.example.slamstatsapp.R
import com.example.slamstatsapp.data.model.TrophiesModel.Trophie
import com.example.slamstatsapp.data.model.TrophiesModel.TrophiesItemsModel
import com.example.slamstatsapp.data.network.TrophiesNetwork.TrophiesService
import javax.inject.Inject

//En este caso se que esta capa es recundante ya que no mapeo los resultados, pero lo dejo para familiarizarme con MVVM
class TrophiesRepository @Inject constructor(private val api:TrophiesService)
{
    suspend fun getTrophiesByPlayerId(playerId: Int): TrophiesItemsModel
    {
        //Tranformar el objeto recibido por el JSON a la clase que pintará el recyclerview
        val trophiesModel = api.getTrophiesByPlayerId(playerId)


        return TrophiesItemsModel(
            listOfNotNull<Trophie>(
                createTrophie(trophiesModel?.rings, R.drawable.larry_obrein),
                createTrophie(trophiesModel?.fmvp, R.drawable.larry_obrein),
                createTrophie(trophiesModel?.mvp, R.drawable.larry_obrein),
                createTrophie(trophiesModel?.allStar, R.drawable.larry_obrein),
                createTrophie(trophiesModel?.dpoy, R.drawable.larry_obrein),
                createTrophie(trophiesModel?.roy, R.drawable.larry_obrein)
            ))
    }

    //Si la cantidad de ese trofeo es mayor a 1 se añadirá a la lista, si no no
    private fun createTrophie(quantity: Int?, imageResId: Int): Trophie? {
        return if (quantity!! > 0)
        {
            Trophie(quantity, imageResId)
        }
        else
        {
            null
        }
    }
}