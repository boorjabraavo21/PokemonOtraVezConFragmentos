package com.turing.alan.pokemonotravezconfragmentos.ui.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonListBinding
import com.turing.alan.pokemonotravezconfragmentos.ui.adapter.PokemonAdapter


class PokemonListFragment : Fragment() {
    private val viewModel:PokemonListViewModel by viewModels()
    private lateinit var binding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater,
            container,
            false

        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.pokemonList
        val adapter = PokemonAdapter(::onShowDetail)

        val observer = Observer<List<Pokemon>> {
            adapter.submitList(it)
        }
        viewModel.pokemonUi.observe(viewLifecycleOwner,observer)
        recyclerView.adapter = adapter
    }

    fun onShowDetail(pokemon:Pokemon) {
        val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemon.name)
        findNavController().navigate(action)
    }
}