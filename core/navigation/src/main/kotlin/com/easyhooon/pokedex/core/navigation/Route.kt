package com.easyhooon.pokedex.core.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

sealed interface Route {
    @Serializable
    data class ListDetail(val name: String) : Route

    @Serializable
    data class FavoritesDetail(val pokemon: PokemonDetailModel) : Route {
        companion object {
            val typeMap = mapOf(
                typeOf<PokemonDetailModel>() to PokemonType,
            )
        }
    }
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object List : MainTabRoute

    @Serializable
    data object Favorites : MainTabRoute
}

val PokemonType = object : NavType<PokemonDetailModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): PokemonDetailModel? {
        return bundle.getString(key)?.let { Json.decodeFromString(it) }
    }

    override fun parseValue(value: String): PokemonDetailModel {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: PokemonDetailModel) {
        bundle.putString(key, Json.encodeToString(PokemonDetailModel.serializer(), value))
    }

    override fun serializeAsValue(value: PokemonDetailModel): String {
        return Json.encodeToString(PokemonDetailModel.serializer(), value)
    }
}
