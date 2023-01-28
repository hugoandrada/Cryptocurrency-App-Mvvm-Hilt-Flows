package com.hugo.andrada.dev.cryptocurrencyapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugo.andrada.dev.cryptocurrencyapp.common.ResultState
import com.hugo.andrada.dev.cryptocurrencyapp.domain.use_cases.GetCoinsUseCase
import com.hugo.andrada.dev.cryptocurrencyapp.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is ResultState.Success -> {
                    _state.value = CoinListState(
                        coins = result.data ?: emptyList(),
                        isLoading = false,
                        error = ""
                    )
                }
                is ResultState.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "unexpected error"
                    )
                }
                is ResultState.Loading -> {
                    _state.value = CoinListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}