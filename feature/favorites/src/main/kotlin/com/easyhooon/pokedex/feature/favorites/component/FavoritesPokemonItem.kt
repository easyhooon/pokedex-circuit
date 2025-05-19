package com.easyhooon.pokedex.feature.favorites.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.component.AutoResizedText
import com.easyhooon.pokedex.core.designsystem.component.NetworkImage
import com.easyhooon.pokedex.core.designsystem.theme.Medium16_SemiBold
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.core.model.PokemonDetailModel
import com.easyhooon.pokedex.core.designsystem.R as designR

@Composable
internal fun FavoritesPokemonItem(
    pokemon: PokemonDetailModel,
    onItemClick: (PokemonDetailModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
            .padding(8.dp)
            .clickable { onItemClick(pokemon) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        NetworkImage(
            imgUrl = pokemon.imageUrl,
            contentDescription = "${pokemon.name} Image",
            loadingPlaceholder = ImageVector.vectorResource(designR.drawable.ic_loading_placeholder),
            failurePlaceholder = ImageVector.vectorResource(designR.drawable.ic_failure_placeholder),
        )
        AutoResizedText(
            text = pokemon.name,
            style = Medium16_SemiBold,
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@ComponentPreview
@Composable
private fun FavoritesPokemonItemPreview() {
    PokedexTheme {
        FavoritesPokemonItem(
            pokemon = PokemonDetailModel(),
            onItemClick = {},
        )
    }
}
