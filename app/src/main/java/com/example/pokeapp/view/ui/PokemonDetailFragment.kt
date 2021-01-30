@file:JvmName("TextUtils")

package com.example.pokeapp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.pokeapp.R
import com.example.pokeapp.databinding.PokemonDetailFragmentBinding
import com.example.pokeapp.service.model.Stat
import com.example.pokeapp.service.model.TypePokemon
import com.example.pokeapp.utils.Constant.ATTACK
import com.example.pokeapp.utils.Constant.DEFENSE
import com.example.pokeapp.utils.Constant.HP
import com.example.pokeapp.utils.Constant.SPEED
import com.example.pokeapp.utils.Constant.SP_ATK
import com.example.pokeapp.utils.Constant.SP_DEF
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
        val name: String = arguments?.getString("name").toString()
        binding.pokemonDetailFrag?.getPokemonDetail(name)
        fillDetail()

    }

    private fun fillDetail() {
        binding.pokemonDetailFrag?.pokemonDetailLive?.observe(viewLifecycleOwner, Observer {
            pokemon_detail_img.loadSvg(it.sprites.other.dream_world.front_default)
            pokemon_name_txt.text = activity?.getString(R.string.pokemon_detail_name, it.name)
            pokemon_type_txt.text =
                activity?.getString(R.string.pokemon_detail_type, formatTypes(it.types))
            pokemon_weight_txt.text = activity?.getString(R.string.pokemon_detail_weight, it.weight)
            pokemon_height_txt.text = activity?.getString(R.string.pokemon_detail_height, it.height)
            formatStats(it.stats)
            progressbar.visibility = View.GONE
            master_ll.visibility = View.VISIBLE
        })
    }

    private fun AppCompatImageView.loadSvg(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(250)
            .data(url)
            .target(this)
            .build()

        imageLoader.enqueue(request)
    }

    private fun formatTypes(types: List<TypePokemon>): String {
        var string: String = ""
        types.forEach {
            string += it.type.name + " "
        }
        return string
    }

    private fun formatStats(stats: List<Stat>) {
        stats.forEach {
            when (it.stat.name) {
                HP -> pokemon_hp_txt.text = activity?.getString(
                    R.string.pokemon_detail_hp,
                    it.base_stat.toString(),
                    it.base_stat.toString()
                )
                ATTACK -> pokemon_atk_txt.text = activity?.getString(
                    R.string.pokemon_detail_attack,
                    it.base_stat.toString()
                )
                DEFENSE -> pokemon_def_txt.text = activity?.getString(
                    R.string.pokemon_detail_defense,
                    it.base_stat.toString()
                )
                SP_ATK -> pokemon_sp_atk_txt.text = activity?.getString(
                    R.string.pokemon_detail_sp_attack,
                    it.base_stat.toString()
                )
                SP_DEF -> pokemon_sp_defense_txt.text = activity?.getString(
                    R.string.pokemon_detail_sp_defense,
                    it.base_stat.toString()
                )
                SPEED -> pokemon_speed_txt.text = activity?.getString(
                    R.string.pokemon_detail_speed,
                    it.base_stat.toString()
                )
            }

        }
    }
}