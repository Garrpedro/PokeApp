package com.example.pokeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.service.model.PokemonList
import com.example.pokeapp.service.repository.PokeRepository

class PokemonListViewModel : ViewModel() {
    val pokemonListLive = MutableLiveData<PokemonList>()

    fun getPokemonList() {
        PokeRepository.getInstance().getPokemonList(0, 20) { isSuccess, response ->
            if (isSuccess) {
                pokemonListLive.value = response
                Log.d("PokemonListViewModel", response?.next + " - " + response?.count)
            } else {
                Log.d("PokemonListViewModel", "Error.....")
            }
        }
    }

}