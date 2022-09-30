package com.itlifelang.jetnewremake.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun AppError(
    modifier: Modifier = Modifier,
    errorMsg: String = stringResource(id = R.string.common_error),
    onReload: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = errorMsg)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(onClick = onReload) {
            Text(text = stringResource(id = R.string.reload))
        }
    }
}

@CompletePreview
@Composable
fun PreviewAppError() {
    AppTheme {
        Surface {
            AppError {}
        }
    }
}