package com.ivantrykosh.app.test_task_for_boosteight.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.AntiFlashWhite
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.LightBlueBackground

@Composable
fun Background() {
    val configuration = LocalConfiguration.current
    Box(modifier = Modifier.fillMaxSize().background(AntiFlashWhite)) {
        Canvas(modifier = Modifier
            .size(0.dp)
            .offset(x = 0.dp, y = Dimens.backgroundCircleVerticalOffset)
            .align(alignment = Alignment.Center),
            onDraw = {
                drawCircle(color = LightBlueBackground, radius = (configuration.screenHeightDp / 2).dp.toPx())
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackgroundPreview() {
    Background()
}