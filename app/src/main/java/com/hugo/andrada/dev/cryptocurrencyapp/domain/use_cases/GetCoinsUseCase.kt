package com.hugo.andrada.dev.cryptocurrencyapp.domain.use_cases

import com.hugo.andrada.dev.cryptocurrencyapp.common.ResultState
import com.hugo.andrada.dev.cryptocurrencyapp.domain.mapper.toCoin
import com.hugo.andrada.dev.cryptocurrencyapp.domain.model.Coin
import com.hugo.andrada.dev.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<ResultState<List<Coin>>> = flow {
        try {
            emit(ResultState.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(ResultState.Success(data = coins))
        } catch (e: IOException) {
            emit(ResultState.Error(e.localizedMessage ?: "unexpected error"))
        } catch (e: HttpException) {
            emit(ResultState.Error(e.localizedMessage ?: "unexpected error"))
        }
    }
}