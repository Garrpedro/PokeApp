@file:JvmName("TextUtils")
package com.example.pokeapp.view.adapter.viewholder

import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.BR
import com.example.pokeapp.R
import com.example.pokeapp.service.model.Result
import com.example.pokeapp.viewmodel.PokemonListViewModel
import kotlinx.android.synthetic.main.item_pokemon_list.view.*

class PokemonListViewHolder constructor(
    private val dataBinding: ViewDataBinding,
    private val pokemonListViewModel: PokemonListViewModel
) : RecyclerView.ViewHolder(dataBinding.root) {

    private val txtPokemonName: TextView = itemView.item_pokemon_list_txt

    fun setup(result: Result, position: Int) {
        dataBinding.setVariable(BR.itemPokemonListResult, result)
        dataBinding.executePendingBindings()

        txtPokemonName.text =
            itemView.context.getString(
                R.string.item_pokemon_list,
                (position + 1),
                result.name.capitalize()
            )

        itemView.setOnClickListener() {
            val bundle = bundleOf("name" to result.name)
            itemView.findNavController()
                .navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment, bundle)
        }
    }
}