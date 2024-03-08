package com.example.slamstatsapp.ui.viewmodel

import com.example.slamstatsapp.data.model.PlayerModel

sealed class ViewState {
    object Loading: ViewState()
    data class Success(val jugadores:List<PlayerModel>) : ViewState()
    data class ErrorLoading(val message:String) : ViewState()
}