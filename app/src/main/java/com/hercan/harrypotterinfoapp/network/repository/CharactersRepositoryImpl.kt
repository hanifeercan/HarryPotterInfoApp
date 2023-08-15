package com.hercan.harrypotterinfoapp.network.repository

import com.hercan.harrypotterinfoapp.network.datasource.characters.CharactersDataSource
import com.hercan.harrypotterinfoapp.network.model.CharacterModel
import com.hercan.harrypotterinfoapp.network.utils.NetworkExt
import com.hercan.harrypotterinfoapp.network.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val dataSource: CharactersDataSource,
) : NetworkExt(), CharactersRepository {

    override suspend fun getAllCharacters(): Flow<Resource<List<CharacterModel>>> {
        return flow {
            emit(safeApiCall { dataSource.getAllCharacters() })
        }
    }

}