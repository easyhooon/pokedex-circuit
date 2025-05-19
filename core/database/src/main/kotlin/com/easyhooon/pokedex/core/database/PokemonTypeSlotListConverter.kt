package com.easyhooon.pokedex.core.database

import androidx.room.TypeConverter
import com.easyhooon.pokedex.core.database.entity.PokemonTypeSlotEntity
import kotlinx.serialization.json.Json

class PokemonTypeSlotListConverter {
    private val json = Json

    @TypeConverter
    fun fromPokemonTypeSlotList(pokemonTypeSlotList: List<PokemonTypeSlotEntity>): String {
        return json.encodeToString(pokemonTypeSlotList)
    }

    @TypeConverter
    fun toPokemonTypeSlotList(pokemonTypeSlotEntityString: String): List<PokemonTypeSlotEntity> {
        return json.decodeFromString(pokemonTypeSlotEntityString)
    }
}
