package com.itlifelang.jetnewremake.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun AppDivider(
    modifier: Modifier = Modifier.padding(horizontal = 16.dp),
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
) {
    Divider(modifier = modifier, color = color)
}

@CompletePreview
@Composable
fun PreviewAppDivider() {
    AppTheme {
        Surface {
            AppDivider()
        }
    }
}
