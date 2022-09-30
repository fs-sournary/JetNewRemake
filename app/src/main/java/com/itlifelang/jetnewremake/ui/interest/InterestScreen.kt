package com.itlifelang.jetnewremake.ui.interest

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestScreen(onOpenDrawer: () -> Unit, isExpandScreen: Boolean) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Interest") },
                navigationIcon = {
                    if (!isExpandScreen) {
                        IconButton(onClick = onOpenDrawer) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_jetnews_logo),
                                contentDescription = stringResource(id = R.string.home_open_drawer_navigation)
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        InterestContent(modifier = Modifier.padding(innerPadding), isExpandScreen = isExpandScreen)
    }
}

@ThemePreview
@Composable
fun PreviewInterestScreen() {
    AppTheme {
        Surface {
            InterestScreen(onOpenDrawer = {}, isExpandScreen = false)
        }
    }
}
