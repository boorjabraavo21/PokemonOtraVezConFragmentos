package com.turing.alan.pokemonotravezconfragmentos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.PokemonItemBinding

class PokemonAdapter(private val onShowDetail:(p:Pokemon)->Unit):ListAdapter<Pokemon, PokemonAdapter.PokemonItemViewHolder>(PokemonDiffCallback) {

    inner class PokemonItemViewHolder(private val binding: PokemonItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(p:Pokemon) {
            binding.pokemonIdText.text = p.id.toString()
            binding.pokemonNameText.text = p.name
            binding.pokemonImage.load(p.front)
            binding.showDetailButton.setOnClickListener {
                onShowDetail(p)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object PokemonDiffCallback:DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem

    }
}