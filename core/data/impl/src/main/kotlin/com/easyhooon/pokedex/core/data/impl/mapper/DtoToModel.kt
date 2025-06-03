package com.easyhooon.pokedex.core.data.impl.mapper

import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.model.PokemonModel
import com.easyhooon.pokedex.core.model.PokemonTypeModel
import com.easyhooon.pokedex.core.model.PokemonTypeSlotModel
import com.easyhooon.pokedex.core.network.response.Pokemon
import com.easyhooon.pokedex.core.network.response.PokemonResponse
import com.easyhooon.pokedex.core.network.response.PokemonType
import com.easyhooon.pokedex.core.network.response.PokemonTypeSlot
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
internal fun Pokemon.toModel(): PokemonModel {
    return PokemonModel(
        name = name,
        url = url,
    )
}

@OptIn(InternalSerializationApi::class)
internal fun PokemonResponse.toModel(): PokemonDetailModel {
    return PokemonDetailModel(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types.map { it.toModel() },
        isFavorite = false,
    )
}

@OptIn(InternalSerializationApi::class)
internal fun PokemonTypeSlot.toModel(): PokemonTypeSlotModel {
    return PokemonTypeSlotModel(
        slot = slot,
        type = type.toModel(),
    )
}

@OptIn(InternalSerializationApi::class)
internal fun PokemonType.toModel(): PokemonTypeModel {
    return PokemonTypeModel(
        name = name,
        url = url,
    )
}
