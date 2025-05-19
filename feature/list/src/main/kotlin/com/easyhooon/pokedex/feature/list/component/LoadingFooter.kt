package com.easyhooon.pokedex.feature.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme

@Composable
internal fun LoadingFooter(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@ComponentPreview
@Composable
private fun LoadingFooterPreview() {
    PokedexTheme {
        LoadingFooter()
    }
}
