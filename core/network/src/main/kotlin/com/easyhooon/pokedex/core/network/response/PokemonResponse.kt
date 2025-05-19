package com.easyhooon.pokedex.core.network.response

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class PokemonResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("height")
    val height: Int,
    @SerialName("weight")
    val weight: Int,
    @SerialName("types")
    val types: List<PokemonTypeSlot>,
)

@InternalSerializationApi
@Serializable
data class PokemonTypeSlot(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: PokemonType,
)

@InternalSerializationApi
@Serializable
data class PokemonType(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)
