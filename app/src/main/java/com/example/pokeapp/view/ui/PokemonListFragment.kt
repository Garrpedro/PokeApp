package com.example.pokeapp.view.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokeapp.R
import com.example.pokeapp.viewmodel.PokemonListViewModel

class PokemonListFragment : Fragment() {

    private lateinit var viewModel: PokemonListViewModel

    companion object {
        fun newInstance() = PokemonListFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pokemon_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PokemonListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}