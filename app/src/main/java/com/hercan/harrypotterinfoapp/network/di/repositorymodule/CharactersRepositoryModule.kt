package com.hercan.harrypotterinfoapp.network.di.repositorymodule

import android.content.Context
import androidx.room.Room
import com.hercan.harrypotterinfoapp.network.datasource.characters.CharactersDataSourceImpl
import com.hercan.harrypotterinfoapp.network.repository.characters.CharactersRepository
import com.hercan.harrypotterinfoapp.network.repository.characters.CharactersRepositoryImpl
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoriteCharactersDao
import com.hercan.harrypotterinfoapp.presentation.room.database.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersRepositoryModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(
        favoriteCharactersDao: FavoriteCharactersDao,
        dataSource: CharactersDataSourceImpl
    ): CharactersRepository {
        return CharactersRepositoryImpl(favoriteCharactersDao, dataSource)
    }

    @Provides
    fun provideFavoriteCharactersDao(favoriteDatabase: FavoriteDatabase): FavoriteCharactersDao {
        return favoriteDatabase.favoriteCharactersDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteCharacterDatabase(@ApplicationContext appContext: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            appContext,
            FavoriteDatabase::class.java,
            "favorite.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}
