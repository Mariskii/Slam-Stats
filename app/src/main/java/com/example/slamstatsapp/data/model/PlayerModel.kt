package com.example.slamstatsapp.data.model

import com.google.gson.annotations.SerializedName

data class PlayerModel(
    @SerializedName("id") val id: Int,
    @SerializedName("nombreCompleto") val nombreCompleto: String,
    @SerializedName("nacionalidad") val nacionalidad: String,
    @SerializedName("altura") val altura: String,
    @SerializedName("peso") val peso: String,
    @SerializedName("posicion") val posicion: String,
    @SerializedName("dorsal") val dorsal: String,
    @SerializedName("fotoCabeza") val fotoCabeza: String,
    @SerializedName("fotoCompleta") val fotoCompleta: String,
    @SerializedName("fnacimiento") val fnacimiento: String
)
