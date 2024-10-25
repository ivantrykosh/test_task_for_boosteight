package com.ivantrykosh.app.test_task_for_boosteight.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.ivantrykosh.app.test_task_for_boosteight.R
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.PastelRed

/**
 * Reusable button
 */
@Preview(showBackground = true)
@Composable
fun BottomButton(onClick: () -> Unit = {}, @StringRes textId: Int = R.string.app_name) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = PastelRed, contentColor = Color.White),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = textId),
            fontSize = Dimens.buttonTextSize,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily(Font(R.font.rubik)),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}