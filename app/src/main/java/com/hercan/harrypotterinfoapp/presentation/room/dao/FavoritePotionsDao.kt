package com.hercan.harrypotterinfoapp.presentation.room.dao

import androidx.room.*
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoritePotionModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePotionsDao {

    @Query("SELECT * FROM favoritePotions")
    fun getPotions(): Flow<List<FavoritePotionModel>>

    @Insert
    fun insertPotion(favorite: FavoritePotionModel)

    @Query("UPDATE favoritePotions SET isFavorite = (:isFavorite) WHERE id IN (:id)")
    fun updatePotion(id: String, isFavorite: Boolean)
}