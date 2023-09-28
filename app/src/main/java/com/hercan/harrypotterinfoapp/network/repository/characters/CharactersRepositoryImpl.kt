package com.hercan.harrypotterinfoapp.network.repository.characters

import androidx.annotation.WorkerThread
import com.hercan.harrypotterinfoapp.network.datasource.characters.CharactersDataSource
import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import com.hercan.harrypotterinfoapp.network.utils.NetworkExt
import com.hercan.harrypotterinfoapp.network.utils.Resource
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoriteCharactersDao
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteCharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val favoriteCharactersDao: FavoriteCharactersDao,
    private val dataSource: CharactersDataSource,
) : NetworkExt(), CharactersRepository {

    override fun getAllCharacters(): Flow<Resource<List<CharacterModel>>> {
        return flow {
            emit(safeApiCall { dataSource.getAllCharacters() })
        }
    }

    var allFavoriteCharacters: Flow<List<FavoriteCharacterModel>> =
        favoriteCharactersDao.getCharacters()

    private fun getAllFavoriteCharacters() {
        allFavoriteCharacters = favoriteCharactersDao.getCharacters()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun update(id: String, isFavorite: Boolean) {
        favoriteCharactersDao.update(id, isFavorite)
        getAllFavoriteCharacters()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(favoriteCharacterModel: FavoriteCharacterModel) {
        favoriteCharactersDao.insert(favoriteCharacterModel)
        getAllCharacters()
    }
}