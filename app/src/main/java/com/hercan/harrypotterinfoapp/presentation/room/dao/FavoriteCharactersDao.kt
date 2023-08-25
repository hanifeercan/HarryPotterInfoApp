package com.hercan.harrypotterinfoapp.presentation.room.dao

import androidx.room.*
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteCharacterModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharactersDao {

    @Query("SELECT * FROM favoriteCharacters")
    fun getCharacters(): Flow<List<FavoriteCharacterModel>>

    @Insert
    fun insert(favorite: FavoriteCharacterModel)

    @Query("UPDATE favoriteCharacters SET isFavorite = (:isFavorite) WHERE id IN (:id)")
    fun update(id: String, isFavorite: Boolean)
}