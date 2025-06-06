package com.easyhooon.pokedex.core.data.impl.mapper

import com.easyhooon.pokedex.core.database.entity.PokemonDetailEntity
import com.easyhooon.pokedex.core.database.entity.PokemonTypeEntity
import com.easyhooon.pokedex.core.database.entity.PokemonTypeSlotEntity
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.model.PokemonTypeModel
import com.easyhooon.pokedex.core.model.PokemonTypeSlotModel

internal fun PokemonDetailEntity.toModel(): PokemonDetailModel {
    return PokemonDetailModel(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types.map { it.toModel() },
    )
}

internal fun PokemonTypeSlotEntity.toModel(): PokemonTypeSlotModel {
    return PokemonTypeSlotModel(
        slot = slot,
        type = type.toModel(),
    )
}

internal fun PokemonTypeEntity.toModel(): PokemonTypeModel {
    return PokemonTypeModel(
        name = name,
        url = url,
    )
}

internal fun PokemonDetailModel.toEntity(): PokemonDetailEntity {
    return PokemonDetailEntity(
        id = id,
        name = name,
        height = height,
        weight = weight,
        types = types.map { it.toEntity() },
    )
}

internal fun PokemonTypeSlotModel.toEntity(): PokemonTypeSlotEntity {
    return PokemonTypeSlotEntity(
        slot = slot,
        type = type.toEntity(),
    )
}

internal fun PokemonTypeModel.toEntity(): PokemonTypeEntity {
    return PokemonTypeEntity(
        name = name,
        url = url,
    )
}
