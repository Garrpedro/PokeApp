package com.example.pokeapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.databinding.ItemPokemonListBinding
import com.example.pokeapp.service.model.Pokemon
import com.example.pokeapp.view.adapter.viewholder.PokemonListViewHolder
import com.example.pokeapp.viewmodel.PokemonListViewModel
import java.util.*

class PokemonListAdapter(private val pokeListViewModel: PokemonListViewModel) :
    RecyclerView.Adapter<PokemonListViewHolder>() {
    private var pokemonList: MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemPokemonListBinding.inflate(inflater, parent, false)
        return PokemonListViewHolder(dataBinding, pokeListViewModel)
    }

    override fun getItemCount() = pokemonList.size


    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.setup(pokemonList[position] as Pokemon, position)
    }

    fun updateList(pokemonList: LinkedList<Pokemon>) {
        if (!pokemonList.isEmpty()) {
            this.pokemonList.addAll(pokemonList)
            notifyDataSetChanged()
        }
    }

}