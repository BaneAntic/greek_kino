package com.greek.kino.data.di

import com.greek.kino.data.api.GreekKinoApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {
    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Singleton
    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi
                .Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build(),
        )

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): GreekKinoApi = retrofit.create(GreekKinoApi::class.java)
}

private val BASE_URL = "https://api.opap.gr/draws/v3.0/"
