package com.hercan.harrypotterinfoapp.presentation.room.dao

import androidx.room.*
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteSpellModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteSpellsDao {

    @Query("SELECT * FROM favoriteSpells")
    fun getSpells(): Flow<List<FavoriteSpellModel>>

    @Insert
    fun insertSpell(favorite: FavoriteSpellModel)

    @Query("UPDATE favoriteSpells SET isFavorite = (:isFavorite) WHERE id IN (:id)")
    fun updateSpell(id: String, isFavorite: Boolean)
}