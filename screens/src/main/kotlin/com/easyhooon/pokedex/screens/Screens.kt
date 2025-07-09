package com.easyhooon.pokedex.screens

import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

abstract class PokedexScreen(val name: String) : Screen {
    override fun toString(): String = name
}

@Parcelize
data object ListScreen : PokedexScreen(name = "List()")

@Parcelize
data class ListDetailScreen(val pokemonName: String) : PokedexScreen(name = "ListDetail()")

@Parcelize
data object FavoritesScreen : PokedexScreen(name = "Favorites()")

@Parcelize
data class FavoritesDetailScreen(val pokemon: PokemonDetailModel) : PokedexScreen(name = "FavoritesDetail()")
