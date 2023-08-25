package com.hercan.harrypotterinfoapp.network.repository.characters

import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import com.hercan.harrypotterinfoapp.network.utils.Resource
import com.hercan.harrypotterinfoapp.presentation.room.model.FavoriteCharacterModel
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getAllCharacters(): Flow<Resource<List<CharacterModel>>>
    suspend fun update(id: String, isFavorite: Boolean)
    suspend fun insert(favoriteCharacterModel: FavoriteCharacterModel)
}