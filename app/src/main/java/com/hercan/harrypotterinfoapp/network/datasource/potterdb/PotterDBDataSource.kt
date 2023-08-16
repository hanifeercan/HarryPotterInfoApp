package com.hercan.harrypotterinfoapp.network.datasource.potterdb

import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import retrofit2.Response

interface PotterDBDataSource {
    suspend fun getAllPotions(): Response<PotionModel>
}