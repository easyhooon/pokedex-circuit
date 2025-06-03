package com.easyhooon.pokedex.feature.favorites_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.easyhooon.pokedex.core.common.ObserveAsEvents
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
import com.easyhooon.pokedex.feature.favorites_detail.preview.FavoritesDetailPreviewParameterProvider
import com.easyhooon.pokedex.feature.favorites_detail.viewmodel.FavoritesDetailUiAction
import com.easyhooon.pokedex.feature.favorites_detail.viewmodel.FavoritesDetailUiEvent
import com.easyhooon.pokedex.feature.favorites_detail.viewmodel.FavoritesDetailUiState
import com.easyhooon.pokedex.feature.favorites_detail.viewmodel.FavoritesDetailViewModel
import com.easyhooon.pokedex.feature.favorites_detail.R
import org.koin.compose.viewmodel.koinViewModel
import com.easyhooon.pokedex.core.designsystem.R as designR

@Composable
internal fun FavoritesDetailRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: FavoritesDetailViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is FavoritesDetailUiEvent.NavigateBack -> popBackStack()
            is FavoritesDetailUiEvent.ShowToast -> Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
        }
    }

    FavoritesDetailScreen(
        padding = padding,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun FavoritesDetailScreen(
    padding: PaddingValues,
    uiState: FavoritesDetailUiState,
    onAction: (FavoritesDetailUiAction) -> Unit,
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding),
        ) {
            PokedexTopAppBar(
                navigationType = TopAppBarNavigationType.Back,
                title = stringResource(R.string.favorites_detail_title),
                navigationIconRes = designR.drawable.ic_arrow_back_gray,
                containerColor = Color.Transparent,
                onNavigationClick = {
                    onAction(FavoritesDetailUiAction.OnBackClick)
                },
            )

            FavoritesDetailContent(
                uiState = uiState,
                onAction = onAction,
            )
        }

        if (uiState.isLoading) {
            LoadingWheel(modifier = Modifier.fillMaxSize())
        }
    }
}

@Suppress("ImplicitDefaultLocale")
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun FavoritesDetailContent(
    uiState: FavoritesDetailUiState,
    onAction: (FavoritesDetailUiAction) -> Unit,
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
                imgUrl = uiState.pokemon.imageUrl,
                contentDescription = "${uiState.pokemon.name} Image",
                loadingPlaceholder = ImageVector.vectorResource(designR.drawable.ic_loading_placeholder),
                failurePlaceholder = ImageVector.vectorResource(designR.drawable.ic_failure_placeholder),
            )
            Text(
                text = uiState.pokemon.name,
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
            uiState.pokemon.types.forEach { type ->
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
                text = String.format("%.1f m", uiState.pokemon.height * 0.1f),
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
                text = String.format("%.1f kg", uiState.pokemon.weight * 0.1f),
                style = Medium16_Mid,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        PokedexOutlinedButton(
            onClick = {
                onAction(FavoritesDetailUiAction.OnFavoritesButtonClick(uiState.pokemon))
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
private fun FavoritesDetailScreenPreview(
    @PreviewParameter(FavoritesDetailPreviewParameterProvider::class)
    favoritesUiState: FavoritesDetailUiState,
) {
    PokedexTheme {
        FavoritesDetailScreen(
            padding = PaddingValues(),
            uiState = favoritesUiState,
            onAction = {},
        )
    }
}
