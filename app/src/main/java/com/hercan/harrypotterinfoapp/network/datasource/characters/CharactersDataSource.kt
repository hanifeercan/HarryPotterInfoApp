package com.hercan.harrypotterinfoapp.network.datasource.characters

import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import retrofit2.Response

interface CharactersDataSource {
    suspend fun getAllCharacters(): Response<List<CharacterModel>>
}
