package com.itlifelang.jetnewremake.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.ui.navigation.Screen
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterest: () -> Unit,
    closeDrawer: () -> Unit
) {
    ModalDrawerSheet(modifier = modifier) {
        Row(modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_jetnews_logo),
                contentDescription = stringResource(id = R.string.cd_jet_new_logo)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
                contentDescription = stringResource(id = R.string.cd_jet_new_logo)
            )
        }
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.home)) },
            selected = currentRoute == Screen.Home.route,
            onClick = { navigateToHome(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.home)
                )
            },
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(id = R.string.interest)) },
            selected = currentRoute == Screen.Interest.route,
            onClick = { navigateToInterest(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            icon = {
                Icon(
                    imageVector = Icons.Filled.ListAlt,
                    contentDescription = stringResource(id = R.string.interest)
                )
            }
        )
    }
}

@ThemePreview
@Composable
fun PreviewAppDrawer() {
    AppTheme {
        Surface {
            AppDrawer(
                currentRoute = Screen.Home.route,
                navigateToHome = {},
                navigateToInterest = {},
                closeDrawer = {}
            )
        }
    }
}
