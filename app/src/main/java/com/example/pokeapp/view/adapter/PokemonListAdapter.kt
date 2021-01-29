package com.example.pokeapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.databinding.ItemPokemonListBinding
import com.example.pokeapp.service.model.Result
import com.example.pokeapp.view.adapter.viewholder.PokemonListViewHolder
import com.example.pokeapp.viewmodel.PokemonListViewModel

class PokemonListAdapter(private val pokeListViewModel: PokemonListViewModel) :
    RecyclerView.Adapter<PokemonListViewHolder>() {
    var pokemonList: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemPokemonListBinding.inflate(inflater, parent, false)
        return PokemonListViewHolder(dataBinding, pokeListViewModel)
    }

    override fun getItemCount() = pokemonList.size


    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.setup(pokemonList[position], position)
    }

    fun updateList(pokemonList: List<Result>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }

}