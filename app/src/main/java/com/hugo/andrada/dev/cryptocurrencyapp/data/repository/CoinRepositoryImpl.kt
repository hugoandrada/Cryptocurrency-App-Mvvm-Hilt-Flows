package com.hugo.andrada.dev.cryptocurrencyapp.data.repository

import com.hugo.andrada.dev.cryptocurrencyapp.data.remote.CoinApiService
import com.hugo.andrada.dev.cryptocurrencyapp.data.remote.CoinDto
import com.hugo.andrada.dev.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApiService
): CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return coinApi.getCoins()
    }
}