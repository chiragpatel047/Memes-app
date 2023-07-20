package com.techydeveloper.memes.module

import com.techydeveloper.memes.api.MemeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getApi() : Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://meme-api.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun apiRequest(retrofit: Retrofit) : MemeApi{
        return retrofit.create(MemeApi::class.java)
    }
}