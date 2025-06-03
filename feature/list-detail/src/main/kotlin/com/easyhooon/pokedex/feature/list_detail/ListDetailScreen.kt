package com.easyhooon.pokedex.feature.list_detail

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
import com.easyhooon.pokedex.core.designsystem.component.NetworkErrorDialog
import com.easyhooon.pokedex.core.designsystem.component.NetworkImage
import com.easyhooon.pokedex.core.designsystem.component.PokedexOutlinedButton
import com.easyhooon.pokedex.core.designsystem.component.PokedexTopAppBar
import com.easyhooon.pokedex.core.designsystem.component.PokemonTypeChip
import com.easyhooon.pokedex.core.designsystem.component.ServerErrorDialog
import com.easyhooon.pokedex.core.designsystem.component.TopAppBarNavigationType
import com.easyhooon.pokedex.core.designsystem.theme.Large20_SemiBold
import com.easyhooon.pokedex.core.designsystem.theme.Medium16_Mid
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.feature.list_detail.R
import com.easyhooon.pokedex.feature.list_detail.preview.ListDetailPreviewParameterProvider
import com.easyhooon.pokedex.feature.list_detail.viewmodel.ErrorType
import com.easyhooon.pokedex.feature.list_detail.viewmodel.ListDetailUiAction
import com.easyhooon.pokedex.feature.list_detail.viewmodel.ListDetailUiEvent
import com.easyhooon.pokedex.feature.list_detail.viewmodel.ListDetailUiState
import com.easyhooon.pokedex.feature.list_detail.viewmodel.ListDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import com.easyhooon.pokedex.core.designsystem.R as designR

@Composable
internal fun ListDetailRoute(
    padding: PaddingValues,
    popBackStack: () -> Unit,
    viewModel: ListDetailViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.uiEvent) { event ->
        when (event) {
            is ListDetailUiEvent.NavigateBack -> popBackStack()
            is ListDetailUiEvent.ShowToast -> Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
        }
    }

    ListDetailScreen(
        padding = padding,
        uiState = uiState,
        onAction = viewModel::onAction,
    )
}

@Composable
internal fun ListDetailScreen(
    padding: PaddingValues,
    uiState: ListDetailUiState,
    onAction: (ListDetailUiAction) -> Unit,
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
                title = stringResource(R.string.list_detail_title),
                navigationIconRes = designR.drawable.ic_arrow_back_gray,
                containerColor = Color.Transparent,
                onNavigationClick = {
                    onAction(ListDetailUiAction.OnBackClick)
                },
            )

            ListDetailContent(
                uiState = uiState,
                onAction = onAction,
            )
        }

        if (uiState.isLoading) {
            LoadingWheel(modifier = Modifier.fillMaxSize())
        }

        if (uiState.isServerErrorDialogVisible) {
            ServerErrorDialog(
                onRetryClick = { onAction(ListDetailUiAction.OnRetryClick(ErrorType.SERVER)) },
            )
        }

        if (uiState.isNetworkErrorDialogVisible) {
            NetworkErrorDialog(
                onRetryClick = { onAction(ListDetailUiAction.OnRetryClick(ErrorType.NETWORK)) },
            )
        }
    }
}

@Suppress("ImplicitDefaultLocale")
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ListDetailContent(
    uiState: ListDetailUiState,
    onAction: (ListDetailUiAction) -> Unit,
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
                onAction(ListDetailUiAction.OnButtonClick(uiState.pokemon))
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
                    imageVector = ImageVector.vectorResource(R.drawable.ic_add_favorites),
                    contentDescription = "Add Favorite Icon",
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.add_favorites),
                    style = Medium16_Mid,
                )
            }
        }
    }
}

@DevicePreview
@Composable
private fun ListDetailScreenPreview(
    @PreviewParameter(ListDetailPreviewParameterProvider::class)
    listUiState: ListDetailUiState,
) {
    PokedexTheme {
        ListDetailScreen(
            padding = PaddingValues(),
            uiState = listUiState,
            onAction = {},
        )
    }
}
