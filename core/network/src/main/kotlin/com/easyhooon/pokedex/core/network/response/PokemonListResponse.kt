package com.easyhooon.pokedex.core.network.response

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@InternalSerializationApi
@Serializable
data class PokemonListResponse(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String,
    @SerialName("previous")
    val previous: String,
    @SerialName("results")
    val results: List<Pokemon>,
)

@InternalSerializationApi
@Serializable
data class Pokemon(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
)
