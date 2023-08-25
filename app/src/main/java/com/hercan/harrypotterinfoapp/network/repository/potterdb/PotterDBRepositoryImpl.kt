package com.hercan.harrypotterinfoapp.network.repository.potterdb

import androidx.annotation.WorkerThread
import com.hercan.harrypotterinfoapp.network.datasource.potterdb.PotterDBDataSource
import com.hercan.harrypotterinfoapp.network.model.potion.PotionDetailModel
import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellDetailModel
import com.hercan.harrypotterinfoapp.network.model.spell.SpellModel
import com.hercan.harrypotterinfoapp.network.utils.NetworkExt
import com.hercan.harrypotterinfoapp.network.utils.Resource
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoritePotionsDao
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoriteSpellsDao
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoritePotionModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteSpellModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PotterDBRepositoryImpl @Inject constructor(
    private val favoritePotionsDao: FavoritePotionsDao,
    private val favoriteSpellsDao: FavoriteSpellsDao,
    private val dataSource: PotterDBDataSource,
) : NetworkExt(), PotterDBRepository {

    override fun getAllPotions(): Flow<Resource<PotionModel>> {
        return flow {
            emit(safeApiCall { dataSource.getAllPotions() })
        }
    }

    override fun getAllSpells(): Flow<Resource<SpellModel>> {
        return flow {
            emit(safeApiCall { dataSource.getAllSpells() })
        }
    }

    override fun getPotionWithSlug(slug: String): Flow<Resource<PotionDetailModel>> {
        return flow {
            emit(safeApiCall { dataSource.getPotionWithSlug(slug) })
        }
    }

    override fun getSpellWithSlug(slug: String): Flow<Resource<SpellDetailModel>> {
        return flow {
            emit(safeApiCall { dataSource.getSpellWithSlug(slug) })
        }
    }

    var allFavoritePotions: Flow<List<FavoritePotionModel>> =
        favoritePotionsDao.getPotions()

    private fun getAllFavoritePotions() {
        allFavoritePotions = favoritePotionsDao.getPotions()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updatePotion(id: String, isFavorite: Boolean) {
        favoritePotionsDao.updatePotion(id, isFavorite)
        getAllFavoritePotions()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertPotion(favoritePotionModel: FavoritePotionModel) {
        favoritePotionsDao.insertPotion(favoritePotionModel)
        getAllFavoritePotions()
    }

    var allFavoriteSpells: Flow<List<FavoriteSpellModel>> =
        favoriteSpellsDao.getSpells()

    private fun getAllFavoriteSpells() {
        allFavoriteSpells = favoriteSpellsDao.getSpells()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updateSpell(id: String, isFavorite: Boolean) {
        favoriteSpellsDao.updateSpell(id, isFavorite)
        getAllFavoriteSpells()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertSpell(favoriteSpellModel: FavoriteSpellModel) {
        favoriteSpellsDao.insertSpell(favoriteSpellModel)
        getAllFavoriteSpells()
    }
}