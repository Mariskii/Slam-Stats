package com.example.slamstatsapp.data.model

import com.google.gson.annotations.SerializedName

data class StatsModel(
    @SerializedName("id") val id: Int,
    @SerializedName("season") val season: String,
    @SerializedName("pj") val pj: Int,
    @SerializedName("ppp") val ppp: Double,
    @SerializedName("tc") val tc: String,
    @SerializedName("tl") val tl: String,
    @SerializedName("rpp") val rpp: Double,
    @SerializedName("app") val app: Double,
    @SerializedName("stl") val stl: Double,
    @SerializedName("blk") val blk: Double,
    @SerializedName("pm3") val pm3: Double,
    @SerializedName("perc3") val perc3: String
)
