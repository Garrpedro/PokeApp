package com.example.pokeapp.service.model

import java.util.*

data class Pokemon(
    val abilities: List<Ability>,
    val base_experience: String,
    val height: String,
    //TODO see tomorrow the correct object
    val held_items: List<Objects>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    //TODO see tomorrow the correct object
    val moves: List<Objects>,
    val name: String,
    val order: Int,
    val stats: List<Stat>,
    val specie: Specie,
    val sprites: Sprites,
    val types: List<TypePokemon>,
    val weight: String
)

//TODO see tomorrow the rest objects
data class Sprites(
    val back_default: String,
    val back_female: String,
    val back_shiny: String,
    val back_shiny_female: String,
    val front_default: String,
    val front_female: String,
    val front_shiny: String,
    val front_shiny_female: String,
    val other: Other

)

data class Specie(
    val name: String,
    val url: String
)

data class Ability(
    val name: String,
    val url: String,
    val is_hidden: Boolean,
    val slot: Int
)

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: CommonResource
)

data class Other(
    val dream_world: DreamWorld,
    val official_artwork: OfficalArtWork
)

data class OfficalArtWork(
    val front_default: String
)

data class DreamWorld(
    val front_default: String,
    val front_female: String
)


data class TypePokemon(
    val slot: Int,
    val type: CommonResource
)

data class CommonResource(
    val name: String,
    val url: String
)