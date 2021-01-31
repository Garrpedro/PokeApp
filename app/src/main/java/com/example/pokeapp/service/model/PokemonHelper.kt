package com.example.pokeapp.service.model

import android.content.Context
import com.example.pokeapp.R
import com.example.pokeapp.utils.Constant
import java.io.Serializable

class PokemonHelper internal constructor(
    pokemon: Pokemon,
    private val context: Context
) : Serializable {

    var pokemonName: String = ""
    var pokemonHP: String = ""
    var pokemonAtk: String = ""
    var pokemonDef: String = ""
    var pokemonSPAtk: String = ""
    var pokemonSPDef: String = ""
    var pokemonSpeed: String = ""
    var pokemonHeight: String = ""
    var pokemonWeight: String = ""
    var pokemonType: String
    var urlSprite: String = pokemon.sprites.other.dream_world.front_default

    init {
        formatStats(pokemon.stats)
        pokemonType = context.getString(R.string.pokemon_detail_type, formatTypes(pokemon.types))
        pokemonName = context.getString(R.string.pokemon_detail_name, pokemon.name)
        pokemonHeight = context.getString(R.string.pokemon_detail_height, pokemon.height)
        pokemonWeight = context.getString(R.string.pokemon_detail_weight, pokemon.weight)
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
                Constant.HP -> pokemonHP = context.getString(
                    R.string.pokemon_detail_hp,
                    it.base_stat.toString(),
                    it.base_stat.toString()
                )
                Constant.ATTACK -> pokemonAtk = context.getString(
                    R.string.pokemon_detail_attack,
                    it.base_stat.toString()
                )
                Constant.DEFENSE -> pokemonDef = context.getString(
                    R.string.pokemon_detail_defense,
                    it.base_stat.toString()
                )
                Constant.SP_ATK -> pokemonSPAtk = context.getString(
                    R.string.pokemon_detail_sp_attack,
                    it.base_stat.toString()
                )
                Constant.SP_DEF -> pokemonSPDef = context.getString(
                    R.string.pokemon_detail_sp_defense,
                    it.base_stat.toString()
                )
                Constant.SPEED -> pokemonSpeed = context.getString(
                    R.string.pokemon_detail_speed,
                    it.base_stat.toString()
                )
            }

        }
    }
}