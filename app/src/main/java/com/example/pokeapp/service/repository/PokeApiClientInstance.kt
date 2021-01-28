package com.example.pokeapp.service.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class PokeApiClientInstance {
    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        val retrofitInstance: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }
}