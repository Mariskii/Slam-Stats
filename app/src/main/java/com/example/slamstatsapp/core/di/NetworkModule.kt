package com.example.slamstatsapp.core.di

import com.example.slamstatsapp.data.network.PlayerNetwork.PlayerApiClient
import com.example.slamstatsapp.data.network.StatsNetwork.StatsApiClient
import com.example.slamstatsapp.data.network.TrophiesNetwork.TrophiesApiClient
import com.example.slamstatsapp.ui.view.PlayerView.PlayerFragmentArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

//CLASE QUE PREPARARÁ LA INYECCIÓN DE DEPENDENCIAS EN LIBRERIAS O CLASES CON INTERFACES
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule
{
    //Provide retrofit
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit
    {
        return  Retrofit.Builder()
            .baseUrl("https://test-production-6b7f.up.railway.app/slamstats/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePlayerApiClient(retrofit: Retrofit): PlayerApiClient
    {
        return retrofit.create(PlayerApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideTrophiesApiClient(retrofit: Retrofit): TrophiesApiClient
    {
        return retrofit.create(TrophiesApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideStatsApiClient(retrofit: Retrofit): StatsApiClient
    {
        return retrofit.create(StatsApiClient::class.java)
    }
}