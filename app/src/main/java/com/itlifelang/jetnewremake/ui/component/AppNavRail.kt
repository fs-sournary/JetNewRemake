package com.itlifelang.jetnewremake.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
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

@Composable
fun AppNavRail(
    modifier: Modifier = Modifier,
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToInterest: () -> Unit
) {
    NavigationRail(
        modifier = modifier,
        header = {
            Icon(
                painter = painterResource(id = R.drawable.ic_jetnews_logo),
                contentDescription = null,
                modifier = Modifier.padding(12.dp)
            )
        }
    ) {
        Spacer(modifier = Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute == Screen.Home.route,
            onClick = navigateToHome,
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.home)
                )
            },
            label = { Text(text = stringResource(id = R.string.home)) }
        )
        NavigationRailItem(
            selected = currentRoute == Screen.Interest.route,
            onClick = navigateToInterest,
            icon = {
                Icon(
                    imageVector = Icons.Filled.ListAlt,
                    contentDescription = stringResource(id = R.string.interest)
                )
            },
            label = { Text(text = stringResource(id = R.string.interest)) }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@ThemePreview
@Composable
fun PreviewAppNavRail() {
    AppTheme {
        Surface {
            AppNavRail(currentRoute = Screen.Home.route, navigateToHome = {}) {}
        }
    }
}
