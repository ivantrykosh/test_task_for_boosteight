package com.ivantrykosh.app.test_task_for_boosteight.presentation.history

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.domain.model.HeartRate
import com.ivantrykosh.app.test_task_for_boosteight.presentation.Background
import com.ivantrykosh.app.test_task_for_boosteight.presentation.BottomButton
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Melon
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.PastelRed
import com.ivantrykosh.app.test_task_for_boosteight.utils.DateUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultHistoryScreen(
    resultHistoryViewModel: ResultHistoryViewModel = hiltViewModel(),
    navigateToHomePage1: () -> Unit = {}
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val getAllHeartRatesState = resultHistoryViewModel.getAllHeartRates.observeAsState()
    val results = getAllHeartRatesState.value?.data ?: emptyList()
    LaunchedEffect(key1 = Unit) {
        resultHistoryViewModel.getAllHeartRates()
    }

    val localDensity = LocalDensity.current
    var columnHeightDp by remember {
        mutableStateOf(0.dp)
    }

    val scrollState = rememberLazyListState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.history),
                        color = Color.White,
                        modifier = Modifier.padding(start = 16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.rubik)),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable { navigateToHomePage1() }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PastelRed)
            )
        }
    ) { paddingValues ->
        Background()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 4.dp, end = 8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = scrollState
                ) {
                    val numberOfResults = results.size
                    if (numberOfResults > 0) {
                        items(numberOfResults) { index ->
                            Box(modifier = Modifier
                                .wrapContentSize()
                                .onGloballyPositioned { coordinates ->
                                    // Set column height using the LayoutCoordinates
                                    columnHeightDp =
                                        with(localDensity) { coordinates.size.height.toDp() }
                                }) {
                                HistoryItemCard(results[index])
                            }
                        }
                    } else {
                        item {
                            Text(
                                text = stringResource(id = R.string.no_measurement),
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.Center)
                                    .onGloballyPositioned { coordinates ->
                                        // Set column height using the LayoutCoordinates
                                        columnHeightDp =
                                            with(localDensity) { coordinates.size.height.toDp() }
                                    },
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(Font(R.font.rubik)),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(
                                    (this@BoxWithConstraints.maxHeight - columnHeightDp * (numberOfResults.coerceAtLeast(
                                        1
                                    ))).coerceAtLeast(80.dp)
                                )
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            BottomButton(
                                onClick = {
                                    resultHistoryViewModel.deleteAllHeartRates()
                                    resultHistoryViewModel.deleteAllHeartRates.observe(lifecycleOwner) {
                                        if (!it.loading) {
                                            resultHistoryViewModel.getAllHeartRates()
                                            resultHistoryViewModel.deleteAllHeartRates.removeObservers(lifecycleOwner)
                                        }
                                    }
                                },
                                textId = R.string.clear_history
                            )
                        }
                    }
                }

                // Draw the custom scrollbar with track and border
                val layoutInfo = scrollState.layoutInfo
                val totalItemsCount = layoutInfo.totalItemsCount
                val visibleItemsCount = layoutInfo.visibleItemsInfo.size

                if (results.isNotEmpty()) {
                    // Define scrollbar height behavior
                    val scrollbarHeight = if (visibleItemsCount >= totalItemsCount) {
                        // If the list is short (no scrolling needed), take the full track height
                        layoutInfo.viewportEndOffset.toFloat()
                    } else {
                        // Calculate the height of the scrollbar based on the visible items and total items
                        val scrollbarHeightRatio = visibleItemsCount.toFloat() / totalItemsCount
                        scrollbarHeightRatio * layoutInfo.viewportEndOffset
                    }

                    // Calculate the scroll position of the scrollbar
                    val firstVisibleItemIndex = scrollState.firstVisibleItemIndex
                    val firstVisibleItemScrollOffset = scrollState.firstVisibleItemScrollOffset

                    // Get the total scrollable area
                    val totalScrollableArea = (totalItemsCount - visibleItemsCount) * layoutInfo.viewportEndOffset

                    // Calculate the exact Y position using both the firstVisibleItemIndex and scrollOffset for smoothness
                    val scrollbarYPosition = if (totalScrollableArea > 0) {
                        (firstVisibleItemIndex.toFloat() * layoutInfo.viewportEndOffset + firstVisibleItemScrollOffset) / totalScrollableArea * layoutInfo.viewportEndOffset
                    } else {
                        0f // No scrolling area, so keep scrollbar at the top
                    }

                    // Apply padding to the bottom of the scrollbar
                    val scrollbarMaxHeight = layoutInfo.viewportEndOffset - localDensity.run { 16.dp.toPx() }

                    // Define the scrollbar width and track width
                    val scrollbarWidth = localDensity.run { 4.dp.toPx() }
                    val trackWidth = localDensity.run { 8.dp.toPx() }
                    val trackColor = Melon
                    val trackBorderColor = Color.Red
                    val scrollbarColor = PastelRed

                    Canvas(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .fillMaxHeight()
                            .offset(x = 4.dp)
                            .width(12.dp) // Slightly wider to accommodate the track and scrollbar
                    ) {
                        // Draw the track with a border and rounded corners
                        val trackHeight = size.height - localDensity.run { 16.dp.toPx() }
                        drawRoundRect(
                            color = trackColor,
                            topLeft = Offset((size.width - trackWidth) / 2, 0f),
                            size = Size(trackWidth, trackHeight),
                            cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx())
                        )
                        // Draw the border for the track
                        drawRoundRect(
                            color = trackBorderColor,
                            topLeft = Offset((size.width - trackWidth) / 2, 0f),
                            size = Size(trackWidth, trackHeight),
                            cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()),
                            style = Stroke(width = 1.dp.toPx())
                        )

                        // Adjust scrollbar size to accommodate bottom padding
                        val adjustedScrollbarHeight = scrollbarHeight.coerceAtMost(scrollbarMaxHeight)

                        // Adjust scrollbar position to respect the bottom padding
                        val adjustedScrollbarYPosition = scrollbarYPosition.coerceIn(0f, scrollbarMaxHeight - adjustedScrollbarHeight)

                        // Draw the scrollbar with rounded corners
                        drawRoundRect(
                            color = scrollbarColor,
                            topLeft = Offset((size.width - scrollbarWidth) / 2, adjustedScrollbarYPosition),
                            size = Size(scrollbarWidth, adjustedScrollbarHeight),
                            cornerRadius = CornerRadius(4.dp.toPx(), 4.dp.toPx()) // Rounded scrollbar
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, backgroundColor = 0xFFB2DEFB)
@Composable
fun HistoryItemCard(heartRate: HeartRate = HeartRate(heartRate = 80, dateTime = 1729785953)) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(4.dp)) {
        Card(
            modifier = Modifier.padding(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .weight(0.5f)
                        .align(Alignment.CenterVertically),
                ) {
                    Text(
                        text = stringResource(id = R.string.bpm_with_parameter, heartRate.heartRate.toString()),
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.rubik)),
                        textAlign = TextAlign.Center
                    )
                }
                Divider(
                    modifier = Modifier
                        .width(5.dp)
                        .height(100.dp)
                        .padding(vertical = 6.dp)
                        .clip(CircleShape),
                    color = PastelRed,
                )
                Column(modifier = Modifier
                    .weight(0.5f)
                    .align(Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(modifier = Modifier.wrapContentSize()) {
                        val stringDateAndTime = DateUtil.getStringDateAndTime(heartRate.dateTime)
                        Text(
                            text = stringDateAndTime.stringTime,
                            color = Color.Gray,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.rubik)),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .wrapContentSize()
                                .offset(y = 2.dp)
                        )
                        Text(
                            text = stringDateAndTime.stringDate,
                            color = Color.Gray,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = FontFamily(Font(R.font.rubik)),
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .wrapContentSize()
                                .offset(y = (-2).dp)
                        )
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultHistoryScreenPreview() {
    ResultHistoryScreen()
}