package com.example.slamstatsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slamstatsapp.data.model.TrophiesModel.TrophiesItemsModel
import com.example.slamstatsapp.data.model.TrophiesModel.TrophiesModel
import com.example.slamstatsapp.domain.GetTrophiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrophiesViewModel @Inject constructor(private var getTrophiesUseCase: GetTrophiesUseCase) : ViewModel()
{
    //Trofeos del jugador mostrado en el fragment de Player
    private val _playerTrophies = MutableStateFlow<TrophiesItemsModel?>(null)
    var playerTrophies :StateFlow<TrophiesItemsModel?> = _playerTrophies

    fun getTrophiesByPlayerId(playerId:Int)
    {
        viewModelScope.launch {
                //Modificar el trofeo del stateFlow
                _playerTrophies.value = getTrophiesUseCase.getTrophiesByPlayerId(playerId)
        }
    }

}