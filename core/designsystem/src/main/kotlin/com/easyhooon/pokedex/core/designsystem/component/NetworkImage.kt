package com.easyhooon.pokedex.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.vectorResource
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.coil.CoilImageState
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.R
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme

@Composable
fun NetworkImage(
    imgUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    loadingPlaceholder: ImageVector? = null,
    failurePlaceholder: ImageVector? = null,
    contentScale: ContentScale = ContentScale.Crop,
    onError: (Boolean) -> Unit = {},
) {
    if (LocalInspectionMode.current) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_loading_placeholder),
            contentDescription = "Example Image Icon",
            modifier = modifier,
        )
    } else {
        CoilImage(
            imageModel = { imgUrl },
            modifier = modifier,
            component = rememberImageComponent {
                +PlaceholderPlugin.Loading(loadingPlaceholder)
                +PlaceholderPlugin.Failure(failurePlaceholder)
            },
            imageOptions = ImageOptions(
                contentScale = contentScale,
                alignment = Alignment.Center,
                contentDescription = contentDescription,
            ),
            onImageStateChanged = { state ->
                if (state is CoilImageState.Failure) {
                    onError(true)
                }
            },
        )
    }
}

@ComponentPreview
@Composable
private fun NetworkImagePreview() {
    PokedexTheme {
        NetworkImage(
            imgUrl = "",
            contentDescription = "",
        )
    }
}
