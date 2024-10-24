package com.ivantrykosh.app.test_task_for_boosteight.presentation.result

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.Background
import com.ivantrykosh.app.test_task_for_boosteight.presentation.HistoryIconButton
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.BrightTurquoise
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.LightBlueBackground
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.MediumSpringGreen
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.PastelRed
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.TartOrange
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.WhiteTransparent
import com.ivantrykosh.app.test_task_for_boosteight.utils.DateUtil

enum class HeartRate(@StringRes val description: Int, val color: Color) {
    SLOWED_DOWN(R.string.slowed_down, BrightTurquoise),
    NORMAL(R.string.normal, MediumSpringGreen),
    SPEEDED_UP(R.string.speeded_up, TartOrange);
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    resultViewModel: ResultViewModel = hiltViewModel(),
    heartRateResult: Int = 70,
    navigateToHomePage1: () -> Unit = {},
    navigateToHistoryPage: () -> Unit = {}
) {
    var timeOfResult by remember {
        mutableLongStateOf(0)
    }
    val result = when {
        heartRateResult < 60 -> HeartRate.SLOWED_DOWN
        heartRateResult <= 100 -> HeartRate.NORMAL
        else -> HeartRate.SPEEDED_UP
    }
    LaunchedEffect(key1 = Unit) {
        timeOfResult = DateUtil.getCurrentTimeInSeconds()
        resultViewModel.insertHeartRate(heartRateResult, timeOfResult)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.result),
                        color = Color.White,
                        modifier = Modifier.padding(end = 8.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.rubik)),
                        textAlign = TextAlign.Center
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PastelRed),
                actions = { HistoryIconButton(onClick = navigateToHistoryPage) }
            )
        }
    ) { paddingValues ->
        Background()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .align(Alignment.Center)
                    .offset(x = 0.dp, y = (-50).dp),
                colors = CardDefaults.cardColors(containerColor = WhiteTransparent)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)) {
                    Column(modifier = Modifier.weight(0.5f)) {
                        Text(
                            text = stringResource(id = R.string.your_result),
                            color = Color.Black,
                            modifier = Modifier.padding(end = 8.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.rubik)),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = stringResource(id = result.description),
                            color = result.color,
                            modifier = Modifier.padding(end = 8.dp),
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.rubik_medium)),
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(modifier =  Modifier.weight(0.3f)) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_access_time_24),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.CenterVertically),
                            tint = Color.Gray
                        )
                        Column(modifier = Modifier
                            .padding(start = 4.dp)
                            .wrapContentWidth(), horizontalAlignment = Alignment.End) {
                            val stringDateAndTime = DateUtil.getStringDateAndTime(timeOfResult)
                            Text(
                                text = stringDateAndTime.stringTime,
                                color = Color.Gray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(Font(R.font.rubik)),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp))
                            Text(
                                text = stringDateAndTime.stringDate,
                                color = Color.Gray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily(Font(R.font.rubik)),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    val targetPosition = when {
                        heartRateResult < 20 -> 0f
                        heartRateResult < 140 -> (heartRateResult - 20) / 120f
                        else -> 1f
                    }
                    MultiColorProgressCanvas(targetPosition)
                }
                Column(modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 8.dp)) {
                    HeartRateInfo(dotColor = BrightTurquoise, infoText = R.string.slowed_down, bpmText = R.string.lower_than_60_bpm, isBold = heartRateResult < 60)
                    HeartRateInfo(dotColor = MediumSpringGreen, infoText = R.string.normal, bpmText = R.string.between_60_and_100_bpm, isBold = heartRateResult in 60..100)
                    HeartRateInfo(dotColor = TartOrange, infoText = R.string.speeded_up, bpmText = R.string.higher_than_100_bpm, isBold = heartRateResult > 100)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PastelRed, contentColor = Color.White),
                    onClick = navigateToHomePage1
                ) {
                    Text(
                        text = stringResource(id = R.string.done),
                        fontSize = Dimens.buttonTextSize,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily(Font(R.font.rubik)),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun HeartRateInfo(dotColor: Color, @StringRes infoText: Int, @StringRes bpmText: Int, isBold: Boolean) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(10f))
                .background(LightBlueBackground)
                .width(120.dp)
                .padding(horizontal = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(8.dp)
                    .background(dotColor)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = stringResource(id = infoText),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.rubik)),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 6.dp)
            )
        }
        Text(
            text = stringResource(id = bpmText),
            color = if (isBold) Color.Black else Color.Gray,
            fontSize = 12.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.rubik)),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen()
}