package com.easyhooon.pokedex.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "favorites")
data class PokemonDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "height")
    val height: Int,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "types")
    val types: List<PokemonTypeSlotEntity>,
)

@Serializable
data class PokemonTypeSlotEntity(
    val slot: Int,
    val type: PokemonTypeEntity,
)

@Serializable
data class PokemonTypeEntity(
    val name: String,
    val url: String,
)
