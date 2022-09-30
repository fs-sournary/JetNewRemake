package com.itlifelang.jetnewremake.ui.interest

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.ThemePreview

@Composable
fun InterestTopicSeparator(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        modifier = modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .semantics { heading() },
        style = MaterialTheme.typography.titleMedium
    )
}

@ThemePreview
@Composable
fun PreviewInterestTopicSeparator() {
    AppTheme {
        Surface {
            InterestTopicSeparator(title = "Android")
        }
    }
}
