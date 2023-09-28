package com.hercan.harrypotterinfoapp.presentation.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoriteCharactersDao
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoritePotionsDao
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoriteSpellsDao
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteCharacterModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoritePotionModel
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteSpellModel

@Database(
    entities = [FavoriteCharacterModel::class, FavoritePotionModel::class, FavoriteSpellModel::class],
    version = 5,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteCharactersDao(): FavoriteCharactersDao
    abstract fun favoritePotionsDao(): FavoritePotionsDao
    abstract fun favoriteSpellsDao(): FavoriteSpellsDao
}