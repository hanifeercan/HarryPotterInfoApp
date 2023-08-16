package com.hercan.harrypotterinfoapp.network.repository.characters

import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import com.hercan.harrypotterinfoapp.network.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(): Flow<Resource<List<CharacterModel>>>
}