package com.example.pokeapp.service.repository

import com.example.pokeapp.service.model.Pokemon
import com.example.pokeapp.service.model.PokemonList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiInterface {

    @GET("pokemon/{name}/")
    fun getPokemon(@Path("name") name: String): Call<Pokemon>

    @GET("pokemon/")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<PokemonList>
}