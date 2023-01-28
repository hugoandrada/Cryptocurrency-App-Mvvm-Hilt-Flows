package com.hugo.andrada.dev.cryptocurrencyapp.presentation.coin_list

import com.hugo.andrada.dev.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
