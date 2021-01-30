package com.example.pokeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.service.model.Pokemon
import com.example.pokeapp.service.repository.PokeRepository

class PokemonDetailViewModel : ViewModel() {
    val pokemonDetailLive = MutableLiveData<Pokemon>()

    fun getPokemonDetail(name: String) {
        PokeRepository.getInstance().getPokemonDetail(name) { isSuccess, response ->
            if (isSuccess) {
                pokemonDetailLive.value = response
                Log.d("PokemonDetailViewModel", response?.name.toString())
            } else {
                Log.d("PokemonDetailViewModel", "Error.....")
            }
        }
    }
}