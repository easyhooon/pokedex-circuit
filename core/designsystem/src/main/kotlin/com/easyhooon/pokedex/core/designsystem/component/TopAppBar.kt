package com.easyhooon.pokedex.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.ComponentPreview
import com.easyhooon.pokedex.core.designsystem.R
import com.easyhooon.pokedex.core.designsystem.theme.Large20_SemiBold
import com.easyhooon.pokedex.core.designsystem.theme.PokedexTheme

enum class TopAppBarNavigationType { None, Back }

@Composable
fun PokedexTopAppBar(
    navigationType: TopAppBarNavigationType,
    modifier: Modifier = Modifier,
    title: String = "",
    titleStyle: TextStyle = Large20_SemiBold,
    @DrawableRes navigationIconRes: Int = R.drawable.ic_arrow_back_dark_gray,
    navigationIconContentDescription: String? = null,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    onNavigationClick: () -> Unit = {},
    elevation: Dp = 0.dp,
) {
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        val icon: @Composable (Modifier, imageVector: ImageVector) -> Unit =
            { modifier, imageVector ->
                IconButton(
                    onClick = onNavigationClick,
                    modifier = modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = navigationIconContentDescription,
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation, RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .background(containerColor)
                .then(modifier),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (navigationType == TopAppBarNavigationType.Back) {
                icon(
                    Modifier,
                    ImageVector.vectorResource(id = navigationIconRes),
                )
                Text(
                    text = title,
                    modifier = Modifier.padding(start = 4.dp, top = 18.dp, bottom = 18.dp),
                    style = titleStyle,
                )
            } else {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    modifier = Modifier.padding(vertical = 18.dp),
                    style = titleStyle,
                )
            }
        }
    }
}

@ComponentPreview
@Composable
private fun PokedexTopAppBarPreview() {
    PokedexTheme {
        PokedexTopAppBar(
            navigationType = TopAppBarNavigationType.None,
            title = "Pokedex",
        )
    }
}

@ComponentPreview
@Composable
private fun PokedexTopAppBarWithBackButtonPreview() {
    PokedexTheme {
        PokedexTopAppBar(
            navigationType = TopAppBarNavigationType.Back,
            title = "Pokedex",
            navigationIconContentDescription = "Navigation back icon",
        )
    }
}
