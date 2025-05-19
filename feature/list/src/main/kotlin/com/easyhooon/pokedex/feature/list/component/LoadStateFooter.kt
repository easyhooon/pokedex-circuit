package com.easyhooon.pokedex.feature.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme

@Composable
internal fun LoadStateFooter(
    modifier: Modifier = Modifier,
    loadState: LoadState,
    onRetryClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        when (loadState) {
            is LoadState.Loading -> LoadingFooter()
            is LoadState.Error -> LoadErrorFooter(onRetryClick = onRetryClick)
            else -> EndFooter()
        }
    }
}

@ComponentPreview
@Composable
private fun LoadStateFooterPreview() {
    PokedexTheme {
        LoadStateFooter(loadState = LoadState.Loading, onRetryClick = {})
    }
}
