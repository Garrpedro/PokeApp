package com.example.pokeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.service.model.PokemonList
import com.example.pokeapp.service.repository.PokeRepository
import com.example.pokeapp.utils.Constant.LIMIT

class PokemonListViewModel : ViewModel() {
    val pokemonListLive = MutableLiveData<PokemonList>()
    var offset = 0

    fun getPokemonList() {
        PokeRepository.getInstance().getPokemonList(offset, LIMIT) { isSuccess, response ->
            if (isSuccess) {
                pokemonListLive.value = response
                Log.d("PokemonListViewModel", response?.next + " - " + response?.count)
            } else {
                Log.d("PokemonListViewModel", "Error.....")
            }
        }
    }

    fun addOffset(int: Int) {
        offset += int
    }
}