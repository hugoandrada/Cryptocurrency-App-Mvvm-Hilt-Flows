package com.hugo.andrada.dev.cryptocurrencyapp.domain.mapper

import com.hugo.andrada.dev.cryptocurrencyapp.data.remote.CoinDto
import com.hugo.andrada.dev.cryptocurrencyapp.domain.model.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}