package com.hercan.harrypotterinfoapp.network.di.datasourcemodule

import com.hercan.harrypotterinfoapp.network.api.PotterDBApi
import com.hercan.harrypotterinfoapp.network.datasource.potterdb.PotterDBDataSource
import com.hercan.harrypotterinfoapp.network.datasource.potterdb.PotterDBDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PotterDBDataSourceModule {

    @Singleton
    @Provides
    fun providePotterDBDataSource(api: PotterDBApi): PotterDBDataSource {
        return PotterDBDataSourceImpl(api)
    }
}