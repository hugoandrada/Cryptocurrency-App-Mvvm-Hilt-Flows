package com.hugo.andrada.dev.cryptocurrencyapp.presentation.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hugo.andrada.dev.cryptocurrencyapp.common.base.BaseFragment
import com.hugo.andrada.dev.cryptocurrencyapp.databinding.FragmentHomeBinding
import com.hugo.andrada.dev.cryptocurrencyapp.presentation.adapter.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var coinAdapter: CoinListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinAdapter = CoinListAdapter()
        setupAdapter()
        collectCoins()
    }

    private fun collectCoins() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { result ->
                    binding.progress.isVisible = result.isLoading
                    binding.errorText.isVisible = result.error.isNotEmpty()
                    val coins = result.coins
                    coinAdapter.submitList(coins)
                }
            }
        }
    }

    private fun setupAdapter() {
        binding.apply {
            recyclerView.apply {
                adapter = coinAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }
}