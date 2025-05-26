package com.easyhooon.pokedex.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.R
import com.easyhooon.pokedex.core.designsystem.theme.Large20_SemiBold
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme
import com.easyhooon.pokedex.core.designsystem.theme.Small14_Mid
import com.easyhooon.pokedex.core.designsystem.theme.XSmall12_Mid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexDialog(
    onDismissRequest: () -> Unit,
    @StringRes titleResId: Int,
    iconResId: Int?,
    iconDescription: String?,
    @StringRes descriptionResId: Int,
    cancelTextResId: Int?,
    confirmTextResId: Int,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
) {
    BasicAlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(27.dp))
            if (iconResId != null && iconDescription != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(iconResId),
                    contentDescription = iconDescription,
                    tint = Color.Unspecified,
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(id = titleResId),
                style = Large20_SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = descriptionResId),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                style = Small14_Mid,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
            ) {
                PokedexButton(
                    onClick = onConfirmClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .then(
                            if (cancelTextResId != null) {
                                Modifier.padding(end = 4.dp)
                            } else {
                                Modifier
                            },
                        ),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White,
                ) {
                    Text(
                        text = stringResource(id = confirmTextResId),
                        color = Color.White,
                        style = XSmall12_Mid,
                    )
                }
                if (cancelTextResId != null) {
                    PokedexButton(
                        onClick = onCancelClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp)
                            .padding(start = 4.dp),
                        containerColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Text(
                            text = stringResource(id = cancelTextResId),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = Small14_Mid,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ServerErrorDialog(
    onRetryClick: () -> Unit,
) {
    PokedexTheme {
        PokedexDialog(
            onDismissRequest = {},
            titleResId = R.string.server_error_title,
            iconResId = R.drawable.ic_caution,
            iconDescription = "Caution Icon",
            descriptionResId = R.string.server_error_description,
            confirmTextResId = R.string.retry,
            cancelTextResId = null,
            onCancelClick = {},
            onConfirmClick = onRetryClick,
        )
    }
}

@Composable
fun NetworkErrorDialog(
    confirmTextResId: Int = R.string.retry,
    onRetryClick: () -> Unit = {},
    onConfirmClick: () -> Unit = {},
) {
    PokedexTheme {
        PokedexDialog(
            onDismissRequest = {},
            titleResId = R.string.network_error_title,
            iconResId = R.drawable.ic_network,
            iconDescription = "Network Error Icon",
            descriptionResId = R.string.network_error_description,
            confirmTextResId = confirmTextResId,
            cancelTextResId = null,
            onCancelClick = {},
            onConfirmClick = if (confirmTextResId == R.string.retry) onRetryClick else onConfirmClick,
        )
    }
}

@ComponentPreview
@Composable
private fun ServerErrorDialogPreview() {
    PokedexTheme {
        ServerErrorDialog(onRetryClick = {})
    }
}

@ComponentPreview
@Composable
private fun NetworkErrorDialogPreview() {
    PokedexTheme {
        NetworkErrorDialog(onRetryClick = {})
    }
}