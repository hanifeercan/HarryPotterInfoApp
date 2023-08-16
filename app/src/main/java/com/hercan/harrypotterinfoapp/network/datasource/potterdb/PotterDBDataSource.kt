package com.hercan.harrypotterinfoapp.network.datasource.potterdb

import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellModel
import retrofit2.Response

interface PotterDBDataSource {
    suspend fun getAllPotions(): Response<PotionModel>
    suspend fun getAllSpells(): Response<SpellModel>
}