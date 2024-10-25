package com.ivantrykosh.app.test_task_for_boosteight.presentation.homepages

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.Background
import com.ivantrykosh.app.test_task_for_boosteight.presentation.HistoryIconButton
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.DarkPastelRed
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.LightPastelRed
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.PastelRed
import com.ivantrykosh.app.test_task_for_boosteight.utils.AppPreferences

/**
 * Represents Homepage1
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun Homepage1Screen(navigateToHomePage2: () -> Unit = { }, navigateToHistoryPage: () -> Unit = { }) {
    val context = LocalContext.current
    // For Camera Permission
    val shouldShowRationale = remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        shouldShowRationale.value = !isGranted
    }
    LaunchedEffect(key1 = cameraPermissionState.status.isGranted) {
        if (cameraPermissionState.status.isGranted) {
            shouldShowRationale.value = false
        } else if (cameraPermissionState.status.shouldShowRationale) {
            shouldShowRationale.value = true
        } else {
            shouldShowRationale.value = false
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
    if (shouldShowRationale.value) {
        CameraPermissionDialog(
            onConfirmClicked = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                context.startActivity(intent)
            }
        )
    }

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
            @StringRes val takeMeasurementText =
                if (AppPreferences.isFirstMeasurement == true) {
                    R.string.take_your_first_measurement
                } else {
                    R.string.take_your_measurement
                }
            Text(
                text = stringResource(id = takeMeasurementText),
                color = Color.Black,
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
                contentDescription = stringResource(id = R.string.heart),
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
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    LightPastelRed,
                                    DarkPastelRed
                                )
                            ),
                            shape = ButtonDefaults.shape
                        )
                    ) {
                    Icon(painter = painterResource(id = R.drawable.mini_heart), contentDescription = stringResource(id = R.string.measure_heart_rate),
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