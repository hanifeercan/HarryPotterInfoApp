package com.hercan.harrypotterinfoapp.network.datasource.potterdb

import com.hercan.harrypotterinfoapp.network.api.PotterDBApi
import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import retrofit2.Response
import javax.inject.Inject

class PotterDBDataSourceImpl @Inject constructor(private val api: PotterDBApi) :
    PotterDBDataSource {

    override suspend fun getAllPotions(): Response<PotionModel> {
        return api.getAllPotions()
    }
}