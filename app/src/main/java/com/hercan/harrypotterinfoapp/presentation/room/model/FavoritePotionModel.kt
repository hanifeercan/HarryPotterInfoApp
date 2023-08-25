package com.hercan.harrypotterinfoapp.presentation.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hercan.harrypotterinfoapp.presentation.model.PotionUIModel


@Entity(tableName = "favoritePotions")
data class FavoritePotionModel(
    @PrimaryKey
    val id: String,
    val characteristics: String,
    val slug: String?,
    val difficulty: String,
    val effect: String,
    val image: String?,
    val ingredients: String,
    val inventors: String,
    val manufacturers: String,
    val name: String,
    val sideEffects: String,
    val time: String,
    val wiki: String?,
    val isFavorite: Boolean = false
)

fun PotionUIModel.toFavoritePotionModel(isFavorite: Boolean): FavoritePotionModel {
    return FavoritePotionModel(
        id ?: "",
        characteristics,
        slug,
        difficulty,
        effect,
        image,
        ingredients,
        inventors,
        manufacturers,
        name,
        sideEffects,
        time,
        wiki,
        isFavorite
    )
}
