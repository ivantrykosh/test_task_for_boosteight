package com.ivantrykosh.app.test_task_for_boosteight.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.ivantrykosh.app.test_task_for_boosteight.presentation.ui.theme.Dimens

/**
 * Button with icon and text
 */
@Composable
fun HistoryIconButton(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick, modifier = Modifier
        .padding(8.dp)
        .widthIn(min = 120.dp)) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = R.string.history),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp),
                fontSize = Dimens.historyTextSize,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.rubik)),
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(id = R.drawable.history),
                contentDescription = stringResource(id = R.string.history),
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(Dimens.historyIconSize),
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun HistoryIconButtonPreview() {
    HistoryIconButton()
}