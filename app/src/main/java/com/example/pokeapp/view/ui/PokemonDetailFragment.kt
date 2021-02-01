@file:JvmName("TextUtils")

package com.example.pokeapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pokeapp.databinding.PokemonDetailFragmentBinding
import com.example.pokeapp.service.model.PokemonHelper
import com.example.pokeapp.utils.Constant.POKEMON
import com.example.pokeapp.utils.GuiUtils.loadSvg
import com.example.pokeapp.viewmodel.PokemonDetailViewModel
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
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressbar.visibility = View.VISIBLE
        val pokemonDetail: PokemonHelper? = arguments?.getSerializable(POKEMON) as PokemonHelper?
        if (pokemonDetail != null) {
            fillDetail(pokemonDetail)
        }

    }

    private fun fillDetail(pokemonDetail: PokemonHelper) {
        pokemon_detail_img.loadSvg(pokemonDetail.urlSprite)
        pokemon_name_txt.text = pokemonDetail.pokemonName
        pokemon_hp_txt.text = pokemonDetail.pokemonHP
        pokemon_type_txt.text = pokemonDetail.pokemonType
        pokemon_weight_txt.text = pokemonDetail.pokemonWeight
        pokemon_height_txt.text = pokemonDetail.pokemonHeight
        pokemon_atk_txt.text = pokemonDetail.pokemonAtk
        pokemon_def_txt.text = pokemonDetail.pokemonDef
        pokemon_sp_atk_txt.text = pokemonDetail.pokemonSPAtk
        pokemon_sp_defense_txt.text = pokemonDetail.pokemonSPDef
        pokemon_speed_txt.text = pokemonDetail.pokemonSpeed
        progressbar.visibility = View.GONE
        master_ll.visibility = View.VISIBLE
    }
}