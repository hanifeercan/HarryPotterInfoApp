package com.hercan.harrypotterinfoapp.network.repository.potterdb

import com.hercan.harrypotterinfoapp.network.model.potion.PotionModel
import com.hercan.harrypotterinfoapp.network.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PotterDBRepository {
    suspend fun getAllPotions(): Flow<Resource<PotionModel>>
}