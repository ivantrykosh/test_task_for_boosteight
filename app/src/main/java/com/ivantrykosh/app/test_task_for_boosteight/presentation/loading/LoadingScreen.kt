package com.ivantrykosh.app.test_task_for_boosteight.presentation.loading

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.Background
import com.ivantrykosh.app.test_task_for_boosteight.presentation.LoadingProgressIndicator
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens

/**
 * Represents Loading screen
 */
@Composable
fun LoadingScreen(navigateToOnboardingOrHomePage1: () -> Unit) {
    Background()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.heart),
            contentDescription = stringResource(id = R.string.heart),
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 0.dp, y = Dimens.heartImageVerticalOffset)
                .scale(Dimens.imageScale)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.Black,
            fontSize = 54.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.rubik_medium)),
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 0.dp, y = 40.dp)
        )
        Box(modifier = Modifier
            .wrapContentSize()
            .padding(32.dp)
            .padding(bottom = 10.dp)
            .align(Alignment.BottomCenter)) {
            LoadingProgressIndicator(durationMillis = 2000, onProgressFinished = navigateToOnboardingOrHomePage1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen(navigateToOnboardingOrHomePage1 = {})
}