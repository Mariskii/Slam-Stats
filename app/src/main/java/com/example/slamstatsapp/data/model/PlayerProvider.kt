package com.example.slamstatsapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

//Almacenamiento unico para todos los jugadores
@Singleton
class PlayerProvider @Inject constructor()
{
    var players:List<PlayerModel> = emptyList()
}