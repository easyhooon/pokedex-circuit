package com.easyhooon.pokedex.core.model

import androidx.compose.runtime.Stable

@Stable
data class PokemonModel(
    val name: String = "",
    val url: String = "",
    val isFavorite: Boolean = false,
) {
    fun getId(): Int = url.split("/").dropLast(1).last().toInt()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getId()}.png"
}
