package com.ivantrykosh.app.test_task_for_boosteight.presentation.homepages

import android.graphics.BlurMaskFilter
import android.view.SurfaceView
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.Background
import com.ivantrykosh.app.test_task_for_boosteight.presentation.LoadingProgressIndicator
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.LightBlue
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.PastelRed
import io.reactivex.disposables.CompositeDisposable
import net.kibotu.heartrateometer.HeartRateOmeter

@Composable
fun Homepage2Screen(navigateToHomepage1: () -> Unit = { }, navigateToResult: (Int) -> Unit = { }) {
    // For heart measurement
    val subscription: CompositeDisposable = remember {
        CompositeDisposable()
    }
    var isFingerDetected by remember {
        mutableStateOf(false)
    }
    val measurements = remember {
        mutableStateListOf<Int>()
    }
    var currentBMP by remember {
        mutableIntStateOf(0)
    }
    val context = LocalContext.current
    val surfaceView = remember { SurfaceView(context) }
    val measurementTimeInMillis = 20000

    // For animating heart
    val scale by rememberInfiniteTransition(label = "ScaleImageInfinite").animateFloat(
        initialValue = Dimens.imageScale,
        targetValue = Dimens.imageScale + 0.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "FloatValueAnimationForScalingImage"
    )

    // For animating digits
    var oldBMP by remember {
        mutableIntStateOf(currentBMP)
    }
    SideEffect {
        oldBMP = currentBMP
    }

    LaunchedEffect(key1 = 0) {
        val sub = HeartRateOmeter()
            .setFingerDetectionListener {
                isFingerDetected = it
                if (!it) {
                    currentBMP = 0
                    measurements.clear()
                }
            }.bpmUpdates(surfaceView).subscribe({ bmp ->
                currentBMP = bmp.value
                if (currentBMP > 0) {
                    measurements.add(currentBMP)
                }
            }, Throwable::printStackTrace)
        subscription.add(sub)
    }

    Background()
    Box(modifier = Modifier.fillMaxSize()) {
        if (!isFingerDetected) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .clickable {
                        if (!subscription.isDisposed) {
                            subscription.dispose()
                        }
                        navigateToHomepage1()
                    }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .offset(y = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(width = 2.dp, color = LightBlue, shape = CircleShape)
                    .clip(CircleShape)
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                AndroidView(factory = { surfaceView })
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(12.dp))
            @StringRes val title = if (isFingerDetected) R.string.measuring_in_progress else R.string.finger_not_detected
            @StringRes val description = if (isFingerDetected) R.string.measuring_in_progress_description else R.string.finger_not_detected_description
            Text(
                text = stringResource(id = title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.rubik_medium)),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = description),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.rubik)),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .wrapContentSize()
                .heightIn(min = 300.dp)
                .align(Alignment.Center)
                .offset(x = 0.dp, y = (-100).dp)
        ) {
            val imageScale = if (isFingerDetected) scale else Dimens.imageScale
            Image(
                painter = painterResource(id = R.drawable.simple_heart),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .scale(imageScale)
//                    .offset(x = 0.dp, y = 25.dp)
//                    .graphicsLayer {
//                        // Set pivot to the center-bottom of the image for scaling
//                        transformOrigin = TransformOrigin(0.5f, 1f)
//                        scaleX = imageScale
//                        scaleY = imageScale
//                    }
                    .drawBehind {
                        // Draw shadow on the bottom of heart image
                        this.drawIntoCanvas {
                            val paint = Paint()
                            val frameworkPaint = paint.asFrameworkPaint()
                            val spreadPixel = 5.dp.toPx()
                            val leftPixel = (0f - spreadPixel) + 40.dp.toPx()
                            val topPixel = (0f - spreadPixel) + this.size.height - 12.dp.toPx()
                            val rightPixel = this.size.width - 40.dp.toPx() + spreadPixel
                            val bottomPixel = this.size.height + 12.dp.toPx() + spreadPixel

                            frameworkPaint.maskFilter = BlurMaskFilter(30.dp.toPx(), BlurMaskFilter.Blur.NORMAL)

                            frameworkPaint.color = PastelRed.toArgb()
                            it.drawRoundRect(
                                left = leftPixel,
                                top = topPixel,
                                right = rightPixel,
                                bottom = bottomPixel,
                                radiusX = 22.dp.toPx(),
                                radiusY = 22.dp.toPx(),
                                paint
                            )
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            ) {
                Row(modifier = Modifier.wrapContentSize()) {
                    val bpmText = if (currentBMP > 0) currentBMP.toString() else stringResource(id = R.string.bpm_placeholder)

                    AnimatedContent(
                        targetState = bpmText,
                        label = "AnimatedNumbers",
                        transitionSpec = {
                            if (oldBMP < currentBMP) {
                                slideInVertically { it } togetherWith slideOutVertically { -it }
                            } else {
                                slideInVertically { -it } togetherWith slideOutVertically { it }
                            }
                        }
                    ) { targetBpm ->
                        Text(
                            text = targetBpm,
                            fontSize = 62.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.rubik_medium)),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            softWrap = false,
                        )
                    }
                }
                Text(
                    text = stringResource(id = R.string.bpm),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily(Font(R.font.rubik_medium)),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
        if (isFingerDetected) {
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(32.dp)
                .offset(x = 0.dp, y = 150.dp)
                .align(Alignment.Center)) {
                LoadingProgressIndicator(durationMillis = measurementTimeInMillis, onProgressFinished = {
                    val averageMeasurement = measurements.average().toInt()
                    isFingerDetected = false
                    currentBMP = 0
                    measurements.clear()
                    if (!subscription.isDisposed) {
                        subscription.dispose()
                    }
                    navigateToResult(averageMeasurement)
                })
            }
        } else {
            Image(
                painter = painterResource(id = R.drawable.phone_and_hand),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(x = 20.dp, y = (-150).dp)
                    .scale(Dimens.imageScale)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Homepage2ScreenPreview() {
    Homepage2Screen()
}