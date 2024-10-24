package com.ivantrykosh.app.test_task_for_boosteight.presentation.result

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.BrightTurquoise
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.MediumSpringGreen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.TartOrange

@Composable
fun MultiColorProgressCanvas(targetIndicatorPosition: Float = 1f) {
    var targetPosition by remember {
        mutableFloatStateOf(0f)
    }
    val currentIndicatorPosition by animateFloatAsState(
        targetValue = targetPosition,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = "IndicatorPositionAnimation"
    )
    LaunchedEffect(key1 = Unit) {
        targetPosition = targetIndicatorPosition
    }

    Canvas(
        modifier = Modifier
            .height(30.dp)
            .fillMaxSize()
    ) {
        val canvasWidth = size.width.toDp()
        val greenSize = canvasWidth / 3
        val yellowSize = canvasWidth / 3
        val redSize = canvasWidth / 3

        val cornerRadius = CornerRadius(10.dp.toPx())

        val firstPath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        offset = Offset(0f, 5.dp.toPx()),
                        size = Size(greenSize.toPx(), 20.dp.toPx()),
                    ),
                    topLeft = cornerRadius,
                    bottomLeft = cornerRadius,
                )
            )
        }
        drawPath(
            path = firstPath,
            color = BrightTurquoise
        )

        drawRect(
            color =  MediumSpringGreen,
            topLeft = Offset(greenSize.toPx(), 5.dp.toPx()),
            size = Size(yellowSize.toPx(), 20.dp.toPx())
        )

        val lastPath = Path().apply {
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        offset = Offset(greenSize.toPx() + yellowSize.toPx(), 5.dp.toPx()),
                        size = Size(redSize.toPx(), 20.dp.toPx()),
                    ),
                    topRight = cornerRadius,
                    bottomRight = cornerRadius,
                )
            )
        }
        drawPath(
            path = lastPath,
            color = TartOrange
        )

        val indicatorPosition = (canvasWidth - 7.dp) * currentIndicatorPosition
        val indicatorPath = Path().apply {
            val indicatorCornerRadius = CornerRadius(4.dp.toPx())
            addRoundRect(
                RoundRect(
                    rect = Rect(
                        offset = Offset(indicatorPosition.toPx(), 2.dp.toPx()),
                        size = Size(6.dp.toPx(), 26.dp.toPx())
                    ),
                    topLeft = indicatorCornerRadius,
                    topRight = indicatorCornerRadius,
                    bottomLeft = indicatorCornerRadius,
                    bottomRight = indicatorCornerRadius
                )
            )
        }
        drawPath(
            path = indicatorPath,
            color = Color.Gray,
            style = Stroke(width = 3.dp.toPx())
        )
        drawPath(
            path = indicatorPath,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MultiColorProgressCanvasPreview() {
    MultiColorProgressCanvas(0.5f)
}