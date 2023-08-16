package com.hercan.harrypotterinfoapp.network.api

import com.hercan.harrypotterinfoapp.network.model.PotionModel
import retrofit2.Response
import retrofit2.http.GET

interface PotterDBApi {

    @GET("potions")
    suspend fun getAllPotions(): Response<PotionModel>
}