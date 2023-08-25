package com.hercan.harrypotterinfoapp.presentation.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hercan.harrypotterinfoapp.presentation.model.SpellUIModel

@Entity(tableName = "favoriteSpells")
data class FavoriteSpellModel(
    @PrimaryKey
    val id: String,
    val category: String,
    val slug: String?,
    val creator: String,
    val effect: String,
    val hand: String,
    val image: String?,
    val incantation: String,
    val light: String,
    val name: String,
    val wiki: String?,
    val isFavorite: Boolean = false
)

fun SpellUIModel.toFavoriteSpellModel(isFavorite: Boolean): FavoriteSpellModel {
    return FavoriteSpellModel(
        id ?: "",
        category,
        slug,
        creator,
        effect,
        hand,
        image,
        incantation,
        light,
        name,
        wiki,
        isFavorite
    )

}