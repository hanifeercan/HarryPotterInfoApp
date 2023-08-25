package com.hercan.harrypotterinfoapp.presentation.room.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hercan.harrypotterinfoapp.presentation.model.CharacterUIModel

@Entity(tableName = "favoriteCharacters")
data class FavoriteCharacterModel(
    @PrimaryKey
    val id: String,
    val actorName: String,
    @DrawableRes
    val aliveId: Int,
    val alternateNames: String?,
    @StringRes
    val typeId: Int,
    val species: String,
    val gender: String,
    @StringRes
    val hogwartsStaffOrStudentId: Int,
    @DrawableRes
    val houseDrawableId: Int,
    @ColorRes
    val houseColorId: Int,
    val image: String?,
    val characterName: String,
    val patronus: String,
    val wandCore: String,
    val isFavorite: Boolean = false
)

fun CharacterUIModel.toFavoriteCharacterModel(isFavorite: Boolean): FavoriteCharacterModel {
    return FavoriteCharacterModel(
        id ?: "",
        actorName,
        aliveId,
        alternateNames,
        typeId,
        species,
        gender,
        hogwartsStaffOrStudentId,
        houseDrawableId,
        houseColorId,
        image,
        characterName,
        patronus,
        wandCore,
        isFavorite
    )
}
