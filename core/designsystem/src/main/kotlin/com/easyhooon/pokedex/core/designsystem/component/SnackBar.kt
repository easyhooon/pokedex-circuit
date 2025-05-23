package com.easyhooon.pokedex.core.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.easyhooon.pokedex.core.designsystem.theme.Small14_Mid

@Composable
fun PokedexSnackBar(
    snackBarData: SnackbarData,
) {
    Snackbar(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = snackBarData.visuals.message,
                textAlign = TextAlign.Center,
                style = Small14_Mid,
            )
        }
    }
}
