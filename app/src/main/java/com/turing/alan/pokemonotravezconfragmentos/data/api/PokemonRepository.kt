package com.turing.alan.pokemonotravezconfragmentos.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface  PokemonApi {
    @GET("api/v2/pokemon/{name}/")
    suspend fun fetchPokemon(@Path("name") name:String):PokemonDetailResponse
    @GET("api/v2/pokemon")
    suspend fun fetchPokemonList():PokemonListResponse
}


class PokemonRepository private constructor(private val api:PokemonApi) {

    private val _pokemon = MutableLiveData<List<PokemonApiModel>>()
    val pokemon: LiveData<List<PokemonApiModel>>
        get() = _pokemon


    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val pokemonApi = retrofit.create(PokemonApi::class.java)
            _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
            return _INSTANCE!!

        }
    }

    suspend fun fetch() {
        /*val pokemonResponse = api.fetchPokemon("1")
        Log.d("DAVID",pokemonResponse.toString())
        _pokemon.value = pokemonResponse*/

        val pokemonListResponse = api.fetchPokemonList()
        val pokemonListApiModel = pokemonListResponse.results.map {
            Log.d("BORJA","Pokemon")
            val detailResponse = api.fetchPokemon(it.name)
            PokemonApiModel(
                detailResponse.id,
                detailResponse.name,
                detailResponse.weight,
                detailResponse.height,
                detailResponse.sprites.frontDefault
            )
        }
        _pokemon.value = pokemonListApiModel
    }


}