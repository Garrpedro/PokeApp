package com.example.pokeapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeapp.service.model.Pokemon
import com.example.pokeapp.service.repository.PokeRepository
import com.example.pokeapp.utils.Constant.LIMIT
import java.util.*

class PokemonListViewModel : ViewModel() {

    val pokemonDetailLive = MutableLiveData<LinkedList<Pokemon>>()
    private val pokemonDetailListAll = LinkedList<Pokemon>()
    private val pokemonDetailList = LinkedList<Pokemon>()
    private var offset = 0
    private var getPokemonNumber = 0
    var loadMore = true

    private fun getPokemonDetail(pokemonName: String) {
        PokeRepository.getInstance().getPokemonDetail(pokemonName) { isSuccess, response ->
            if (isSuccess) {
                if (response != null) {
                    pokemonDetailList.add(response)
                    getPokemonNumber += 1
                    if (getPokemonNumber == LIMIT) {
                        pokemonDetailList.sortBy { it.order }
                        getPokemonNumber = 0
                        pokemonDetailListAll.addAll(pokemonDetailList)
                        pokemonDetailLive.value = pokemonDetailList
                        pokemonDetailLive.value = LinkedList()
                    }
                }
                Log.d("PokemonListViewModel", response?.name + " - " + response?.order)
            } else {
                Log.d("PokemonListViewModel", "Error.....")
            }
        }

    }

    fun getPokemonList() {
        PokeRepository.getInstance().getPokemonList(offset, LIMIT) { isSuccess, response ->
            if (isSuccess && loadMore) {
                loadMore = false
                pokemonDetailList.clear()
                response?.results?.forEach { pokemonName ->
                    getPokemonDetail(pokemonName.name)
                }
                Log.d("PokemonListViewModel", response?.next + " - " + response?.count)
            } else {
                Log.d("PokemonListViewModel", "Error.....")
            }
        }
    }

    fun addOffset(int: Int) {
        offset += int
    }

    fun getPokemonLisAllSize(): Int {
        return pokemonDetailListAll.size
    }
}