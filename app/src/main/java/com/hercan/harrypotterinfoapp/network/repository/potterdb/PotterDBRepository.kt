package com.hercan.harrypotterinfoapp.network.repository.potterdb

import com.hercan.harrypotterinfoapp.network.model.potion.PotionDetailModel
import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellModel
import com.hercan.harrypotterinfoapp.network.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PotterDBRepository {
    fun getAllPotions(): Flow<Resource<PotionModel>>
    fun getAllSpells(): Flow<Resource<SpellModel>>
    fun getPotionWithSlug(slug: String): Flow<Resource<PotionDetailModel>>
}