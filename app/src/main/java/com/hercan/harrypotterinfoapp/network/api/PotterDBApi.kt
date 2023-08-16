package com.hercan.harrypotterinfoapp.network.api

import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PotterDBApi {

    @GET("potions")
    suspend fun getAllPotions(): Response<PotionModel>
}