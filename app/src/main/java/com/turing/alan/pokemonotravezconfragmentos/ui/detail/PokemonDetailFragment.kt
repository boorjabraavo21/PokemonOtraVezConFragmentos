package com.turing.alan.pokemonotravezconfragmentos.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.turing.alan.pokemonotravezconfragmentos.R
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.FragmentPokemonDetailBinding


class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private val args:PokemonDetailFragmentArgs by navArgs()
    private val viewModel:PokemonDetailViewModel by activityViewModels()
    private val observer = Observer<Pokemon> {
        binding.pokemonImage.load(it.front)
        binding.pokemonNameText.text = it.name
        binding.pokemonHeight.text = it.height.toString()+" dm"
        binding.pokemonWeight.text = it.weight.toString()+" kg"
        binding.topAppBar.title = it.name
        binding.topAppBar.setOnClickListener {
            findNavController().popBackStack(R.id.pokemonListFragment, false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonDetailBinding.inflate(inflater,
            container,
            false

        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchPokemon(args.pokemon)
        viewModel.pokemonUI.observe(viewLifecycleOwner, observer)
    }
}