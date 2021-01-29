package com.example.pokeapp.view.ui

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.databinding.PokemonDetailFragmentBinding
import com.example.pokeapp.databinding.PokemonListFragmentBinding
import com.example.pokeapp.view.adapter.PokemonListAdapter
import com.example.pokeapp.viewmodel.PokemonDetailViewModel
import com.example.pokeapp.viewmodel.PokemonListViewModel
import kotlinx.android.synthetic.main.item_pokemon_list.*
import kotlinx.android.synthetic.main.pokemon_detail_fragment.*

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: PokemonDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonDetailFragmentBinding.inflate(inflater, container, false).apply {
            pokemonDetailFrag = ViewModelProvider(this@PokemonDetailFragment)
                .get(PokemonDetailViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name: String = arguments?.getString("name").toString()
        binding.pokemonDetailFrag?.getPokemonDetail(name)

        fillDetail()
    }

    private fun fillDetail() {
        binding.pokemonDetailFrag?.pokemonDetailLive?.observe(viewLifecycleOwner, Observer {
            activity?.let { context ->
                Glide.with(context)
                    .load(it.sprites.front_default)
                    .into(pokemon_detail_img)
            };
        })
    }

}