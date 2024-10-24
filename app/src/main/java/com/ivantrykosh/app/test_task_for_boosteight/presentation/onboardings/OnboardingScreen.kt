package com.ivantrykosh.app.test_task_for_boosteight.presentation.onboardings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.Background
import com.ivantrykosh.app.test_task_for_boosteight.presentation.BottomButton
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.PastelRed
import kotlinx.coroutines.launch

enum class OnboardingScreens {
    OnboardingInfo1, OnboardingInfo2, OnboardingInfo3;
}

@Composable
fun OnboardingScreen(navigateToHome: () -> Unit) {
    val numberOfOnboardings = OnboardingScreens.entries.size
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { numberOfOnboardings })
    val scope = rememberCoroutineScope()

    Background()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->
            OnboardingInfo(onboardingScreen = OnboardingScreens.entries[page])
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            ExpandablePagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(16.dp)
                    .offset(x = 0.dp, y = (-2).dp)
                    .align(Alignment.CenterHorizontally),
                count = numberOfOnboardings
            )
            BottomButton(
                onClick = {
                    if (OnboardingScreens.entries[pagerState.currentPage] != OnboardingScreens.OnboardingInfo3) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        navigateToHome()
                    }
                },
                textId = when (OnboardingScreens.entries[pagerState.currentPage]) {
                    OnboardingScreens.OnboardingInfo1, OnboardingScreens.OnboardingInfo3 -> R.string.start
                    OnboardingScreens.OnboardingInfo2 -> R.string.continue_
                }
            )
        }
    }
}

@Composable
fun ExpandablePagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    count: Int,
    width: Dp = Dimens.onboardingCircleIndicatorDiameter,
    height: Dp = Dimens.onboardingCircleIndicatorDiameter,
    activeLineWidth: Dp = 36.dp,
    circleSpacing: Dp = 8.dp
) {
    val radius = CornerRadius(x = 25f, y = 25f)

    Box(modifier = modifier) {
        Canvas(modifier = modifier.align(Alignment.Center)) {
            val spacing = circleSpacing.toPx()
            val dotWidth = width.toPx()
            val dotHeight = height.toPx()

            val activeDotWidth = activeLineWidth.toPx()
            var x = center.x - 40.dp.toPx()
            val y = center.y

            repeat(count) { pageIndex ->
                val posOffset = pagerState.pageOffset
                val dotOffset = posOffset % 1
                val current = posOffset.toInt()

                val factor = (dotOffset * (activeDotWidth - dotWidth))

                // Determine width of the current dot
                val calculatedWidth = when {
                    pageIndex == current -> activeDotWidth - factor
                    pageIndex - 1 == current || (pageIndex == 0 && posOffset > count - 1) -> dotWidth + factor
                    else -> dotWidth
                }

                drawIndicator(x, y, calculatedWidth, dotHeight, radius, isCurrentPage = pageIndex == current)
                x += calculatedWidth + spacing
            }
        }
    }
}

/**
 * Calculate page offset
 */
val PagerState.pageOffset: Float
    get() = this.currentPage + this.currentPageOffsetFraction

/**
 * Draw the round indicator on the canvas
 * @param x starting X coordinate for drawing
 * @param y starting Y coordinate for drawing
 * @param width width of figure to draw
 * @param height height of figure to draw
 * @param radius corner radius of figure
 * @param isCurrentPage boolean value to select corresponding color for figure
 */
private fun DrawScope.drawIndicator(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    radius: CornerRadius,
    isCurrentPage: Boolean,
) {
    val rect = RoundRect(
        left = x,
        top = y - height / 2,
        right = x + width,
        bottom = y + height / 2,
        cornerRadius = radius
    )
    val path = Path().apply { addRoundRect(rect) }
    drawPath(path = path, color = if (isCurrentPage) PastelRed else Color.LightGray)
}

@Composable
fun OnboardingInfo(onboardingScreen: OnboardingScreens) {
    @DrawableRes val imageRes: Int
    @StringRes val title: Int
    @StringRes val description: Int
    when (onboardingScreen) {
        OnboardingScreens.OnboardingInfo1 -> {
            imageRes = R.drawable.onboarding1_image
            title = R.string.your_pressure_tracker
            description = R.string.your_pressure_tracker_description
        }
        OnboardingScreens.OnboardingInfo2 -> {
            imageRes = R.drawable.onboarding2_image
            title = R.string.personalized_advices
            description = R.string.personalized_advices_description
        }
        OnboardingScreens.OnboardingInfo3 -> {
            imageRes = R.drawable.onboarding3_image
            title = R.string.reminder
            description = R.string.reminder_description
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 0.dp, y = Dimens.onboardingImageVerticalOffset)
                .scale(Dimens.imageScale)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 22.dp)
                .align(Alignment.Center)
                .offset(x = 0.dp, y = Dimens.onboardingTextOffset)
        ) {
            Text(
                text = stringResource(id = title),
                fontSize = Dimens.onboardingTitleTextSize,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.rubik_medium)),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.onboardingTitleDescriptionSpacerHeight))
            Text(
                text = stringResource(id = description),
                fontSize = Dimens.onboardingDescriptionTextSize,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.rubik)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navigateToHome = {})
}