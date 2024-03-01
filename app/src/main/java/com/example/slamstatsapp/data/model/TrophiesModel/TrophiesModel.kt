package com.example.slamstatsapp.data.model.TrophiesModel

import com.google.gson.annotations.SerializedName

data class TrophiesModel(
    @SerializedName("id") val id:Int,
    @SerializedName("anillos") val rings:Int,
    @SerializedName("fmvp") val fmvp:Int,
    @SerializedName("mvp") val mvp:Int,
    @SerializedName("allstar") val allStar:Int,
    @SerializedName("dpoy") val dpoy:Int,
    @SerializedName("roy") val roy:Int
)
