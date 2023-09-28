package com.hercan.harrypotterinfoapp.network.repository.potterdb

import com.hercan.harrypotterinfoapp.network.model.potion.PotionDetailModel
import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellDetailModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellModel
import com.hercan.harrypotterinfoapp.network.utils.Resource
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoritePotionModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteSpellModel
import kotlinx.coroutines.flow.Flow

interface PotterDBRepository {
    fun getAllPotions(): Flow<Resource<PotionModel>>
    fun getAllSpells(): Flow<Resource<SpellModel>>
    fun getPotionWithSlug(slug: String): Flow<Resource<PotionDetailModel>>
    fun getSpellWithSlug(slug: String): Flow<Resource<SpellDetailModel>>
    suspend fun updatePotion(id: String, isFavorite: Boolean)
    suspend fun insertPotion(favoritePotionModel: FavoritePotionModel)
    suspend fun updateSpell(id: String, isFavorite: Boolean)
    suspend fun insertSpell(favoriteSpellModel: FavoriteSpellModel)
}