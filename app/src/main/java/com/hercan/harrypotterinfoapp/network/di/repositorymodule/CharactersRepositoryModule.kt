package com.hercan.harrypotterinfoapp.network.di.repositorymodule

import com.hercan.harrypotterinfoapp.network.datasource.characters.CharactersDataSourceImpl
import com.hercan.harrypotterinfoapp.network.repository.CharactersRepository
import com.hercan.harrypotterinfoapp.network.repository.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersRepositoryModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(dataSource: CharactersDataSourceImpl): CharactersRepository {
        return CharactersRepositoryImpl(dataSource)
    }
}
