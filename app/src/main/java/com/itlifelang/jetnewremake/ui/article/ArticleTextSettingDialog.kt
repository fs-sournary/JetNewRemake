package com.itlifelang.jetnewremake.ui.article

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun ArticleTextSettingDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.close))
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.article_text_setting_title),
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(text = stringResource(id = R.string.article_text_setting_description))
        },
        shape = MaterialTheme.shapes.small
    )
}

@CompletePreview
@Composable
fun PreviewArticleTextSettingDialog() {
    AppTheme {
        Surface {
            ArticleTextSettingDialog {}
        }
    }
}
