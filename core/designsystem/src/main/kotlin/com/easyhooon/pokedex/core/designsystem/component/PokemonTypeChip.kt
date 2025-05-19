package com.easyhooon.pokedex.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.theme.Medium16_Mid
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun PokemonTypeChip(
    typeName: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(4.dp),
        shape = RoundedCornerShape(36.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 13.dp, vertical = 5.dp),
        ) {
            Text(
                text = typeName,
                style = Medium16_Mid,
            )
        }
    }
}

@ComponentPreview
@Composable
private fun PokemonTypeChipPreview() {
    PokedexTheme {
        PokemonTypeChip(
            typeName = "grass",
        )
    }
}
