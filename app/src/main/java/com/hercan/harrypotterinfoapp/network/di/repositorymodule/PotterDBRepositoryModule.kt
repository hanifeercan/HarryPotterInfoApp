package com.hercan.harrypotterinfoapp.network.di.repositorymodule

import com.hercan.harrypotterinfoapp.network.datasource.potterdb.PotterDBDataSourceImpl
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepository
import com.hercan.harrypotterinfoapp.network.repository.potterdb.PotterDBRepositoryImpl
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
    fun providePotterDBRepository(dataSource: PotterDBDataSourceImpl): PotterDBRepository {
        return PotterDBRepositoryImpl(dataSource)
    }
}
