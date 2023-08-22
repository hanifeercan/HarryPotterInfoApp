package com.hercan.harrypotterinfoapp.network.api

import com.hercan.harrypotterinfoapp.network.model.potion.PotionDetailModel
import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PotterDBApi {

    @GET("potions")
    suspend fun getAllPotions(): Response<PotionModel>

    @GET("spells")
    suspend fun getAllSpells(): Response<SpellModel>

    @GET("potions/{slug}")
    suspend fun getPotionWithSlug(@Path("slug") slug: String): Response<PotionDetailModel>

}