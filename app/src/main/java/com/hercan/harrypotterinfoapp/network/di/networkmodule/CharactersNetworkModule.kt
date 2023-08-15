package com.hercan.harrypotterinfoapp.network.di.networkmodule

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hercan.harrypotterinfoapp.network.api.CharactersApi
import com.hercan.harrypotterinfoapp.network.di.networkmodule.BaseUrl.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersNetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder().client(client).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
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
    const val BASE_URL = "https://hp-api.onrender.com/api/"
}
