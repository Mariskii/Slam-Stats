package com.example.slamstatsapp.core.di

import com.example.slamstatsapp.data.network.PlayerApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun providePlayerApiClient(retrofit: Retrofit):PlayerApiClient
    {
        return retrofit.create(PlayerApiClient::class.java)
    }
}