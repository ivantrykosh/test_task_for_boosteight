package com.ivantrykosh.app.test_task_for_boosteight.presentation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.LightRedProgress
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.RedProgress

@Composable
fun LoadingProgressIndicator(
    durationMillis: Int = 2000,
    onProgressFinished: () -> Unit = { },
) {
    var progress by remember {
        mutableFloatStateOf(0f)
    }
    val currentPercentage by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing),
        label = "Loading",
        finishedListener = { onProgressFinished() }
    )
    LaunchedEffect(key1 = Unit) {
        progress = 1f
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        LinearProgressIndicator(
            progress = currentPercentage,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(50))
                .border(1.dp, Color.Red, RoundedCornerShape(50)),
            color = RedProgress,
            trackColor = LightRedProgress,
            strokeCap = StrokeCap.Round,
        )
        Text(
            text = "${(currentPercentage * 100).toInt()}%",
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize(),
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.rubik_medium))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingProgressIndicatorPreview() {
    LoadingProgressIndicator()
}