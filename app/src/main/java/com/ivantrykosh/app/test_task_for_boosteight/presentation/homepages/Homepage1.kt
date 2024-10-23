package com.ivantrykosh.app.test_task_for_boosteight.presentation.homepages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.Background
import com.ivantrykosh.app.test_task_for_boosteight.presentation.HistoryIconButton
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.DarkPastelRed
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.LightPastelRed
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.PastelRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage1Screen(navigateToHomePage2: () -> Unit = { }, navigateToHistoryPage: () -> Unit = { }) {
    Scaffold(topBar = {
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(containerColor = PastelRed),
            actions = { HistoryIconButton(onClick = navigateToHistoryPage) }
        )
    }) { paddingValues ->
        Background()
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.take_your_first_measurement),
                fontSize = Dimens.homepage1TitleTextSize,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.rubik_medium)),
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 0.dp, y = 40.dp),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(x = 0.dp, y = (-50).dp)
                    .scale(Dimens.imageScale)
            )
            FilledIconButton(
                onClick = navigateToHomePage2,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(80.dp)
                    .offset(y = (-17).dp)
                    .shadow(
                        (2).dp,
                        shape = ButtonDefaults.shape,
                        spotColor = Color.Black,
                        ambientColor = Color.Transparent
                    ),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent, contentColor = Color.Unspecified)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(colors = listOf(LightPastelRed, DarkPastelRed)),
                            shape = ButtonDefaults.shape
                        )
                    ) {
                    Icon(painter = painterResource(id = R.drawable.mini_heart), contentDescription = "",
                        Modifier
                            .scale(1f)
                            .offset(y = (3).dp)
                            .align(Alignment.Center))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Homepage1ScreenPreview() {
    Homepage1Screen()
}