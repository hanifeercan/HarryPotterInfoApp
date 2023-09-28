package com.hercan.harrypotterinfoapp.network.di.networkmodule

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hercan.harrypotterinfoapp.network.api.CharactersApi
import com.hercan.harrypotterinfoapp.network.api.PotterDBApi
import com.hercan.harrypotterinfoapp.network.di.networkmodule.BaseUrl.BASE_URL_CHARACTERS
import com.hercan.harrypotterinfoapp.network.di.networkmodule.BaseUrl.BASE_URL_POTTERDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CharacterApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PotterApi

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @CharacterApi
    fun providesCharactersRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL_CHARACTERS)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    @PotterApi
    fun providesPotterDBRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL_POTTERDB)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesCharactersApi(@CharacterApi retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    @Singleton
    @Provides
    fun providesPotterDBApi(@PotterApi retrofit: Retrofit): PotterDBApi {
        return retrofit.create(PotterDBApi::class.java)
    }

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()
}

object BaseUrl {
    const val BASE_URL_CHARACTERS = "https://hp-api.onrender.com/api/"
    const val BASE_URL_POTTERDB = "https://api.potterdb.com/v1/"
}
