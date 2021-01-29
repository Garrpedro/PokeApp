@file:JvmName("TextUtils")

package com.example.pokeapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.databinding.PokemonDetailFragmentBinding
import com.example.pokeapp.service.model.CommonResource
import com.example.pokeapp.service.model.TypePokemon
import com.example.pokeapp.viewmodel.PokemonDetailViewModel
import kotlinx.android.synthetic.main.pokemon_detail_fragment.*
import java.util.function.Consumer

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
            }
            pokemon_name_txt.text = it.name
            pokemon_type_txt.text = formatTypes(it.types)
            pokemon_weight_txt.text = activity?.getString(R.string.pokemon_detail_weight, it.weight)
            pokemon_height_txt.text = activity?.getString(R.string.pokemon_detail_height, it.height)
        })
    }

    private fun formatTypes(types: List<TypePokemon>): String {
        var string: String = ""
        types.forEach {
            string += it.type.name + " "
        }
        return string
    }

}