package com.hercan.harrypotterinfoapp.network.repository.potterdb

import com.hercan.harrypotterinfoapp.network.datasource.potterdb.PotterDBDataSource
import com.hercan.harrypotterinfoapp.network.model.PotionModel
import com.hercan.harrypotterinfoapp.network.utils.NetworkExt
import com.hercan.harrypotterinfoapp.network.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PotterDBRepositoryImpl @Inject constructor(
    private val dataSource: PotterDBDataSource,
) : NetworkExt(), PotterDBRepository {

    override suspend fun getAllPotions(): Flow<Resource<PotionModel>> {
        return flow {
            emit(safeApiCall { dataSource.getAllPotions() })
        }
    }
}