package com.hercan.harrypotterinfoapp.network.datasource.potterdb

import com.hercan.harrypotterinfoapp.network.api.PotterDBApi
import com.hercan.harrypotterinfoapp.network.model.potion.PotionDetailModel
import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellDetailModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellModel
import retrofit2.Response
import javax.inject.Inject

class PotterDBDataSourceImpl @Inject constructor(private val api: PotterDBApi) :
    PotterDBDataSource {

    override suspend fun getAllPotions(): Response<PotionModel> {
        return api.getAllPotions()
    }

    override suspend fun getAllSpells(): Response<SpellModel> {
        return api.getAllSpells()
    }

    override suspend fun getPotionWithSlug(slug: String): Response<PotionDetailModel> {
        return api.getPotionWithSlug(slug)
    }

    override suspend fun getSpellWithSlug(slug: String): Response<SpellDetailModel> {
        return api.getSpellWithSlug(slug)
    }
}