package com.hercan.harrypotterinfoapp.network.di.datasourcemodule

import com.hercan.harrypotterinfoapp.network.api.CharactersApi
import com.hercan.harrypotterinfoapp.network.datasource.characters.CharactersDataSource
import com.hercan.harrypotterinfoapp.network.datasource.characters.CharactersDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersDataSourceModule {

    @Singleton
    @Provides
    fun provideHarryPotterCharacterDataSource(api: CharactersApi): CharactersDataSource {
        return CharactersDataSourceImpl(api)
    }
}