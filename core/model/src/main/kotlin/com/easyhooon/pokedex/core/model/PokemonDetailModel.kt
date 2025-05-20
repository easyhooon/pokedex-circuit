package com.easyhooon.pokedex.core.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class PokemonDetailModel(
    val id: Int = 0,
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<PokemonTypeSlotModel> = emptyList(),
    val isFavorite: Boolean = false,
): Parcelable {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

@Parcelize
@Stable
data class PokemonTypeSlotModel(
    val slot: Int = 0,
    val type: PokemonTypeModel = PokemonTypeModel(),
): Parcelable

@Parcelize
@Stable
data class PokemonTypeModel(
    val name: String = "",
    val url: String = "",
): Parcelable
