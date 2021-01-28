package com.example.pokeapp.service.repository

import com.example.pokeapp.service.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetDataInterface {

    @GET("pokemon/{id}/")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon/")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<List<Pokemon>>
}