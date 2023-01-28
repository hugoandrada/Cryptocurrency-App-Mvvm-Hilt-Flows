package com.hugo.andrada.dev.cryptocurrencyapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hugo.andrada.dev.cryptocurrencyapp.databinding.ItemRowLayoutBinding
import com.hugo.andrada.dev.cryptocurrencyapp.domain.model.Coin

class CoinListAdapter : ListAdapter<Coin, CoinListAdapter.CoinViewHolder>(CoinDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            ItemRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class CoinViewHolder(private val binding: ItemRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin) {
            binding.apply {
                coinName.text = coin.name
                coinInfo.text = coin.symbol
            }
        }
    }

    class CoinDiffUtil: DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

}