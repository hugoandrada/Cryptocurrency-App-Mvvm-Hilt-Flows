package com.hugo.andrada.dev.cryptocurrencyapp.di

import com.hugo.andrada.dev.cryptocurrencyapp.common.Constants
import com.hugo.andrada.dev.cryptocurrencyapp.data.remote.CoinApiService
import com.hugo.andrada.dev.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.hugo.andrada.dev.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): CoinApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: CoinApiService): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}