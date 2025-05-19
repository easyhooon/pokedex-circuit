package com.easyhooon.pokedex.feature.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.easyhooon.pokedex.core.model.PokemonModel
import com.easyhooon.pokedex.core.designsystem.R as designR

@Composable
internal fun PokemonItem(
    pokemon: PokemonModel,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray),
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .clickable { onItemClick(pokemon.name) },
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
        if (pokemon.isFavorite) {
            Icon(
                imageVector = ImageVector.vectorResource(designR.drawable.ic_selected_favorites),
                contentDescription = "Favorite Icon",
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp),
            )
        }
    }
}

@ComponentPreview
@Composable
private fun PokemonItemPreview() {
    PokemonItem(
        pokemon = PokemonModel(
            name = "ivysaur",
            url = "https://pokeapi.co/api/v2/pokemon/2/",
        ),
        onItemClick = {},
    )
}
