package com.example.slamstatsapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slamstatsapp.data.model.PlayerModel
import com.example.slamstatsapp.data.model.TeamModels.TeamPlayerModel
import com.example.slamstatsapp.domain.GetPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

//COMUNICA EL VIEW Y EL MODEL CUANDO EL USUARIO INTERACTUA O SE OBTIENE INFORMACION
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private var getPlayersUseCase:GetPlayersUseCase
) : ViewModel()
{
    //Estado de las peticiones
    private val _stateUI = MutableStateFlow<ViewState>(ViewState.Loading)
    var stateUI:StateFlow<ViewState> = _stateUI

    //Jugadores sugeridos al usuario en el homefragment
    private val _playersSugerencies = MutableStateFlow<List<PlayerModel>>(emptyList())
    var playersSugerencies:StateFlow<List<PlayerModel>> = _playersSugerencies

    //Lista de jugadores buscados
    private val _searchedPlayers = MutableStateFlow<List<PlayerModel>>(emptyList())
    var searchedPlayers :StateFlow<List<PlayerModel>> = _searchedPlayers

    //Jugador buscado por id, usado para playerfragment
    private val _searchedPlayerById = MutableStateFlow<PlayerModel?>(null)
    var searchedPlayerById :StateFlow<PlayerModel?> = _searchedPlayerById

    //Equipos en los que ha estado el jugador
    private val _teamsPlayer = MutableStateFlow<List<TeamPlayerModel>>(emptyList())
    var teamsPlayer: StateFlow<List<TeamPlayerModel>> = _teamsPlayer

    //Jugador del dia del homefragment
    private val _playerOfTheDay = MutableStateFlow<PlayerModel?>(null)
    var playerOfTheDay :StateFlow<PlayerModel?> = _playerOfTheDay

    fun onCreate() {
        viewModelScope.launch {
            try {
                // Iniciar dos tareas asíncronas
                val playersDeferred = async {
                    val players = mutableListOf<PlayerModel>()
                    for (n in 0..7) {
                        getPlayersUseCase.getRandomPlayerById()?.let { players.add(it) }
                    }
                    players
                }
                val playerOfTheDayDeferred = async {
                    getPlayersUseCase.getRandomPlayerById()
                }

                // Esperar a que ambas tareas asíncronas se completen
                val players = playersDeferred.await()
                val playerOfTheDay = playerOfTheDayDeferred.await()

                // Asignar los valores al StateFlow después de que las tareas asíncronas hayan terminado
                _playersSugerencies.value = players
                _playerOfTheDay.value = playerOfTheDay

                // Establecer el estado de éxito después de asignar los valores
                if(_playersSugerencies.value.isEmpty() && _playerOfTheDay.value == null)
                    _stateUI.value = ViewState.ErrorLoading("Sin conexión")
                else
                    _stateUI.value = ViewState.Success(_playersSugerencies.value)
            }
            catch (e: IOException) {
                _stateUI.value = ViewState.ErrorLoading("${e.message}")
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
            val result = getPlayersUseCase.getPlayersById(playerId)

            _searchedPlayerById.value = result
        }
    }

    fun getPlayerTeams(playerId: Int)
    {
        viewModelScope.launch {
            _teamsPlayer.value = getPlayersUseCase.getPlayerTeams(playerId)
        }
    }
}