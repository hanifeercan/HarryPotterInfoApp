package com.hercan.harrypotterinfoapp.presentation.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT id FROM favorite WHERE id IN (:id) AND type IN (:type)")
    fun findId(id: String, type: String): String

    @Insert
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)
}