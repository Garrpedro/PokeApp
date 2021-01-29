package com.example.pokeapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapp.databinding.PokemonListFragmentBinding
import com.example.pokeapp.view.adapter.PokemonListAdapter
import com.example.pokeapp.viewmodel.PokemonListViewModel
import kotlinx.android.synthetic.main.pokemon_list_fragment.*

class PokemonListFragment : Fragment() {

    private lateinit var adapter: PokemonListAdapter
    private lateinit var binding: PokemonListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonListFragmentBinding.inflate(inflater, container, false).apply {
            pokemonListFrag = ViewModelProvider(this@PokemonListFragment)
                .get(PokemonListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonListFrag?.getPokemonList()

        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        binding.pokemonListFrag?.pokemonListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it.results)
        })

    }

    private fun setupAdapter() {
        val viewModel = binding.pokemonListFrag
        if (viewModel != null) {
            adapter = PokemonListAdapter(binding.pokemonListFrag!!)
            val layoutManager = LinearLayoutManager(activity)
            gridPokemon.layoutManager = layoutManager
            gridPokemon.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    layoutManager.orientation
                )
            )
            gridPokemon.adapter = adapter
        }

    }
}