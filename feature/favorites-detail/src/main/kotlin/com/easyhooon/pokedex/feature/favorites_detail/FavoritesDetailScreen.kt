package com.easyhooon.pokedex.feature.favorites_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.DevicePreview
import com.easyhooon.pokedex.core.designsystem.component.LoadingWheel
import com.easyhooon.pokedex.core.designsystem.component.NetworkImage
import com.easyhooon.pokedex.core.designsystem.component.PokedexOutlinedButton
import com.easyhooon.pokedex.core.designsystem.component.PokedexTopAppBar
import com.easyhooon.pokedex.core.designsystem.component.PokemonTypeChip
import com.easyhooon.pokedex.core.designsystem.component.TopAppBarNavigationType
import com.easyhooon.pokedex.core.designsystem.theme.Large20_SemiBold
import com.easyhooon.pokedex.core.designsystem.theme.Medium16_Mid
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.screens.FavoritesDetailScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import dagger.hilt.android.components.ActivityRetainedComponent
import com.easyhooon.pokedex.core.designsystem.R as designR

@CircuitInject(FavoritesDetailScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun FavoritesDetail(
    state: FavoritesDetailUiState,
    modifier: Modifier = Modifier,
) {
    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .systemBarsPadding(),
        ) {
            PokedexTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = stringResource(R.string.favorites_detail_title),
                navigationIconRes = designR.drawable.ic_arrow_back_gray,
                containerColor = Color.Transparent,
                onNavigationClick = {
                    state.eventSink(FavoritesDetailUiEvent.OnBackClick)
                },
            )

            FavoritesDetailContent(state = state)
        }

        if (state.isLoading) {
            LoadingWheel(modifier = Modifier.fillMaxSize())
        }
    }
}

@Suppress("ImplicitDefaultLocale")
@Composable
internal fun FavoritesDetailContent(
    state: FavoritesDetailUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NetworkImage(
                imgUrl = state.pokemon.imageUrl,
                contentDescription = "${state.pokemon.name} Image",
                loadingPlaceholder = ImageVector.vectorResource(designR.drawable.ic_loading_placeholder),
                failurePlaceholder = ImageVector.vectorResource(designR.drawable.ic_failure_placeholder),
            )
            Text(
                text = state.pokemon.name,
                style = Large20_SemiBold,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.type),
            style = Medium16_Mid,
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow {
            state.pokemon.types.forEach { type ->
                PokemonTypeChip(
                    typeName = type.type.name,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.default_info),
            style = Medium16_Mid,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.height),
                style = Medium16_Mid,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = String.format("%.1f m", state.pokemon.height * 0.1f),
                style = Medium16_Mid,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.weight),
                style = Medium16_Mid,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = String.format("%.1f kg", state.pokemon.weight * 0.1f),
                style = Medium16_Mid,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        PokedexOutlinedButton(
            onClick = {
                state.eventSink(FavoritesDetailUiEvent.OnFavoritesButtonClick)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(48.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_remove_favorites),
                    contentDescription = "Remove Favorites Icon",
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.remove_favorite),
                    style = Medium16_Mid,
                )
            }
        }
    }
}

@DevicePreview
@Composable
private fun FavoritesDetailScreenPreview() {
    PokedexTheme {
        FavoritesDetail(
            state = FavoritesDetailUiState(eventSink = {}),
        )
    }
}
