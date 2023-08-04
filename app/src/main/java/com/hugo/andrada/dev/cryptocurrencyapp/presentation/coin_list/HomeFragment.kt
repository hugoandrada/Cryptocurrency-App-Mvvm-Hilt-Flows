package com.hugo.andrada.dev.cryptocurrencyapp.presentation.coin_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hugo.andrada.dev.cryptocurrencyapp.R
import com.hugo.andrada.dev.cryptocurrencyapp.databinding.FragmentHomeBinding
import com.hugo.andrada.dev.cryptocurrencyapp.presentation.adapter.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: CoinListViewModel by viewModels()
    private lateinit var coinAdapter: CoinListAdapter

    private var currentBinging: FragmentHomeBinding? = null
    private val binding get() = currentBinging!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentBinging = FragmentHomeBinding.bind(view)
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

    override fun onDestroy() {
        super.onDestroy()
        currentBinging = null
    }
}