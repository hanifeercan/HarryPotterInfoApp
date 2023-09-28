package com.hercan.harrypotterinfoapp.network.datasource.characters

import com.hercan.harrypotterinfoapp.network.api.CharactersApi
import com.hercan.harrypotterinfoapp.network.model.character.CharacterModel
import retrofit2.Response
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(private val api: CharactersApi) :
    CharactersDataSource {

    override suspend fun getAllCharacters(): Response<List<CharacterModel>> {
        return api.getAllCharacters()
    }
}