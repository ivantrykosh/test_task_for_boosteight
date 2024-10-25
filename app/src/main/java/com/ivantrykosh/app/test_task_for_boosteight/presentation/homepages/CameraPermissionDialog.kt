package com.ivantrykosh.app.test_task_for_boosteight.presentation.homepages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivantrykosh.app.test_task_for_boosteight.R

/**
 * Represents AlertDialog with information that the user have to allow camera in settings
 */
@Preview(showBackground = true)
@Composable
fun CameraPermissionDialog(onConfirmClicked: () -> Unit = {}) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            Text(
                text = stringResource(id = R.string.ok),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.rubik_medium)),
                modifier = Modifier.wrapContentSize().padding(end = 8.dp).clickable { onConfirmClicked() },
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.rubik_medium)),
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.camera_permission_is_needed_rationale),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.rubik)),
                modifier = Modifier.wrapContentSize(),
                textAlign = TextAlign.Left,
                color = Color.Black
            )
        }
    )
}