package com.easyhooon.pokedex.core.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Stable
@Serializable
data class PokemonDetailModel(
    val id: Int = 0,
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<PokemonTypeSlotModel> = emptyList(),
    val isFavorite: Boolean = false,
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

@Stable
@Serializable
data class PokemonTypeSlotModel(
    val slot: Int = 0,
    val type: PokemonTypeModel = PokemonTypeModel(),
)

@Stable
@Serializable
data class PokemonTypeModel(
    val name: String = "",
    @Serializable(with = UriSerializer::class)
    val url: String = "",
)

object UriSerializer : KSerializer<String> {
    override val descriptor = PrimitiveSerialDescriptor("Uri", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(URLEncoder.encode(value, StandardCharsets.UTF_8.name()))
    }

    override fun deserialize(decoder: Decoder): String {
        return URLDecoder.decode(decoder.decodeString(), StandardCharsets.UTF_8.name())
    }
}
