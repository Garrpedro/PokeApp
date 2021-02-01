@file:JvmName("GuiUtils")

package com.example.pokeapp.view.adapter.viewholder

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.BR
import com.example.pokeapp.R
import com.example.pokeapp.service.model.Pokemon
import com.example.pokeapp.service.model.PokemonHelper
import com.example.pokeapp.utils.Constant.POKEMON
import com.example.pokeapp.utils.GuiUtils.loadSvg
import com.example.pokeapp.viewmodel.PokemonListViewModel
import kotlinx.android.synthetic.main.item_pokemon_list.view.*

class PokemonListViewHolder constructor(
    private val dataBinding: ViewDataBinding,
    private val pokemonListViewModel: PokemonListViewModel
) : RecyclerView.ViewHolder(dataBinding.root) {

    private val txtPokemonId: TextView = itemView.item_pokemon_list_id_txt
    private val txtPokemonName: TextView = itemView.item_pokemon_list_name_txt
    private val imgPokemon: AppCompatImageView = itemView.item_pokemon_list_img


    fun setup(result: Pokemon, position: Int) {
        dataBinding.setVariable(BR.itemPokemonListResult, result)
        dataBinding.executePendingBindings()

        txtPokemonName.visibility = View.VISIBLE
        imgPokemon.visibility = View.VISIBLE

        txtPokemonId.text =
            itemView.context.getString(
                R.string.item_pokemon_list_id,
                result.id.toString().padStart(3, '0')
            )
        txtPokemonName.text =
            itemView.context.getString(
                R.string.item_pokemon_list_name,
                result.name.capitalize()
            )


        imgPokemon.loadSvg(result.sprites.other.dream_world.front_default)

        itemView.setOnClickListener() {
            val bundle = Bundle(1)
            bundle.putSerializable(POKEMON, PokemonHelper(result, itemView.context))
            itemView.findNavController()
                .navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment, bundle)
        }
    }
}
