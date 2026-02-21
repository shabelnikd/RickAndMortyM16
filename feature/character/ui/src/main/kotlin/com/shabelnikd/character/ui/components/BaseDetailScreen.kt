package com.shabelnikd.character.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shabelnikd.character.ui.components.animations.animationAlphaScaleRotateY
import com.shabelnikd.character.ui.core.UiState
import kotlinx.coroutines.delay

@Composable
fun <T> BaseDetailScreen(
    itemKey: Any,
    modifier: Modifier = Modifier,
    itemState: UiState<T>,
    animate: Boolean = true,
    progressDelayMillis: Long = 1200,
    content: @Composable ColumnScope.(T) -> Unit
) {
    val showProgress = remember(itemKey) { mutableStateOf(false) }

    LaunchedEffect(itemKey) {
        delay(progressDelayMillis)
        showProgress.value = true
    }

    when (itemState) {
        is UiState.Success<T> -> {
            val item = itemState.data

            Column(
                modifier = modifier
                    .animationAlphaScaleRotateY(
                        itemKey = itemKey,
                        animate = animate
                    )
                    .background(
                        Color.Transparent, shape = MaterialTheme.shapes.small
                    )
                    .padding(16.dp),

                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content.invoke(this@Column, item)
            }
        }

        is UiState.NotLoaded -> {
            if (showProgress.value) {
                Text("Не загружено")
            }
        }

        is UiState.Loading -> {
            if (showProgress.value) {
                Box(modifier = modifier, contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        color = ProgressIndicatorDefaults.circularColor
                    )
                }
            }

        }

        is UiState.Error -> {
            if (showProgress.value) {
                Text(itemState.message)
            }
        }
    }
}