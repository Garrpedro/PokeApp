package com.example.pokeapp.service.repository

import com.example.pokeapp.service.model.Pokemon
import com.example.pokeapp.service.model.PokemonList
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class PokeRepository {

    // GET pokemon list
    fun getPokemonList(
        offset: Int,
        limit: Int,
        onResult: (isSuccess: Boolean, response: PokemonList?) -> Unit
    ) {

        PokeApiInstance.pokeInstance.getPokemonList(offset, limit)
            .enqueue(object : Callback<PokemonList> {
                override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                    onResult(false, null)
                }

                override fun onResponse(call: Call<PokemonList>, response: Response<PokemonList>) {
                    if (response.isSuccessful)
                        onResult(true, response.body()!!)
                    else
                        onResult(false, null)
                }
            }
            )
    }

    // GET pokemon list
    fun getPokemonDetail(
        name: String,
        onResult: (isSuccess: Boolean, response: Pokemon?) -> Unit
    ) {

        PokeApiInstance.pokeInstance.getPokemon(name)
            .enqueue(object : Callback<Pokemon> {
                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    onResult(false, null)
                }

                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if (response.isSuccessful)
                        onResult(true, response.body())
                    else
                        onResult(false, null)
                }
            }
            )
    }

    companion object {
        private var INSTANCE: PokeRepository? = null
        fun getInstance() = INSTANCE
            ?: PokeRepository().also {
                INSTANCE = it
            }
    }
}