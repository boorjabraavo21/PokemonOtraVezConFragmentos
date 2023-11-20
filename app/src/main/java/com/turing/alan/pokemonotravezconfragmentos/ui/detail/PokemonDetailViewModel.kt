package com.turing.alan.pokemonotravezconfragmentos.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import kotlinx.coroutines.launch


class PokemonDetailViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _pokemonUI = MutableLiveData<Pokemon>()
    val pokemonUI: LiveData<Pokemon>
        get() = _pokemonUI

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            val pokemonResponse = repository.getPokemon(name)
            _pokemonUI.value = Pokemon(pokemonResponse.id, pokemonResponse.name, pokemonResponse.weight, pokemonResponse.height, pokemonResponse.front)
        }
    }
}