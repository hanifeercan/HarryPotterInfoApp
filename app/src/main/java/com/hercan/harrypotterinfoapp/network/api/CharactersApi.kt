package com.hercan.harrypotterinfoapp.network.api

import com.hercan.harrypotterinfoapp.network.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {

    @GET("characters")
    suspend fun getAllCharacters(): Response<List<CharacterModel>>
}