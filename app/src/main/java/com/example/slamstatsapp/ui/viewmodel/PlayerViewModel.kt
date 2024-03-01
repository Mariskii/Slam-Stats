package com.example.slamstatsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.domain.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//COMUNICA EL VIEW Y EL MODEL CUANDO EL USUARIO INTERACTUA O SE OBTIENE INFORMACION
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private var getPlayersUseCase:GetPlayersUseCase
) : ViewModel()
{
    val playerModel = MutableLiveData<List<PlayerModel>?>()

    //Lista de jugadores buscados
    private val _searchedPlayers = MutableStateFlow<List<PlayerModel>>(emptyList())
    var searchedPlayers :StateFlow<List<PlayerModel>> = _searchedPlayers

    //Jugador buscado por id
    private val _searchedPlayerById = MutableStateFlow<PlayerModel>(PlayerModel(1,"1","1","1","1","2","2","","","https://developer.android.com/static/images/guide/fragments/fragment-view-lifecycle.png?hl=es-419"))
    var searchedPlayerById :StateFlow<PlayerModel> = _searchedPlayerById

    fun onCreate()
    {
        viewModelScope.launch {
            val result = getPlayersUseCase.getAllPlayers()

            if(!result.isNullOrEmpty()){
                playerModel.postValue(result)
            }
        }
    }

    fun searchPlayers(playerName:String)
    {
        viewModelScope.launch {
            val result = getPlayersUseCase.getPlayersByName(playerName)

            if (result.isNotEmpty())
            {
                _searchedPlayers.value = result
            }
        }
    }

    fun searchPlayerById(playerId: Int)
    {
        viewModelScope.launch{
            val res = _searchedPlayerById
            val result = getPlayersUseCase.getPlayersById(playerId)

            _searchedPlayerById.value = result
        }
    }
}