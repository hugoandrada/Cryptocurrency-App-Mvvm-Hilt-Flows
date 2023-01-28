package com.hugo.andrada.dev.cryptocurrencyapp.domain.repository

import com.hugo.andrada.dev.cryptocurrencyapp.data.remote.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
}