package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.ui.util.interceptKey
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeSearch(
    searchInput: String,
    onSearchInputChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    showTrailingIcon: Boolean,
    onTrailingIconClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = searchInput,
        onValueChange = onSearchInputChange,
        modifier = modifier
            .fillMaxWidth()
            .interceptKey(Key.Enter) {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        placeholder = { Text(text = stringResource(id = R.string.search_article)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (showTrailingIcon) {
                IconButton(onClick = onTrailingIconClick) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
        })
    )
}

@CompletePreview
@Composable
fun PreviewHomeSearch() {
    AppTheme {
        Surface {
            HomeSearch(searchInput = "", onSearchInputChange = {}, showTrailingIcon = false) {}
        }
    }
}
