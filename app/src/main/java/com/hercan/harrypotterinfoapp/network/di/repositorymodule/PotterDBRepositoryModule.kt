package com.hercan.harrypotterinfoapp.network.di.repositorymodule

import com.hercan.harrypotterinfoapp.network.datasource.potterdb.PotterDBDataSourceImpl
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepository
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoritePotionsDao
import com.hercan.harrypotterinfoapp.presentation.room.dao.FavoriteSpellsDao
import com.hercan.harrypotterinfoapp.presentation.room.database.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PotterDBRepositoryModule {

    @Provides
    @Singleton
    fun providePotterDBRepository(
        favoritePotionsDao: FavoritePotionsDao,
        favoriteSpellsDao: FavoriteSpellsDao,
        dataSource: PotterDBDataSourceImpl
    ): PotterDBRepository {
        return PotterDBRepositoryImpl(favoritePotionsDao, favoriteSpellsDao, dataSource)
    }

    @Provides
    fun provideFavoritePotionsDao(favoriteDatabase: FavoriteDatabase): FavoritePotionsDao {
        return favoriteDatabase.favoritePotionsDao()
    }

    @Provides
    fun provideFavoriteSpellsDao(favoriteDatabase: FavoriteDatabase): FavoriteSpellsDao {
        return favoriteDatabase.favoriteSpellsDao()
    }
}
