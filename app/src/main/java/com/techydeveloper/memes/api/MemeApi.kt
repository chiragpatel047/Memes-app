package com.techydeveloper.memes.api

import com.techydeveloper.memes.model.MemeModel
import retrofit2.Response
import retrofit2.http.GET

interface MemeApi {

    @GET("/gimme")
    suspend fun getRandomMeme() : Response<MemeModel>

}