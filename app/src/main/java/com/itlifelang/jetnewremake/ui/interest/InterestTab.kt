package com.itlifelang.jetnewremake.ui.interest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.ThemePreview

@Composable
fun InterestTab(
    isExpandScreen: Boolean,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    onTabSelected: (Int) -> Unit
) {
    if (!isExpandScreen) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier,
            divider = { Divider(color = Color.Transparent) }
        ) {
            InterestTabContent(selectedTabIndex = selectedTabIndex, onTabSelected = onTabSelected)
        }
    } else {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = modifier.fillMaxWidth(),
            contentColor = MaterialTheme.colorScheme.primary,
            edgePadding = 0.dp,
            divider = { Divider(color = Color.Transparent) }
        ) {
            InterestTabContent(
                selectedTabIndex = selectedTabIndex,
                onTabSelected = onTabSelected,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun InterestTabContent(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    InterestSection.values().forEachIndexed { index, section ->
        val selected = selectedTabIndex == index
        val color = if (selected) colorScheme.primary else colorScheme.onSurface.copy(alpha = 0.6f)
        Tab(
            selected = selected,
            onClick = { onTabSelected(index) },
            modifier = Modifier.heightIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(id = section.titleId),
                color = color,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.paddingFromBaseline(top = 20.dp)
            )
        }
    }
}

@ThemePreview
@Composable
fun PreviewInterestTab() {
    AppTheme {
        Surface {
            InterestTab(isExpandScreen = false, selectedTabIndex = 0, onTabSelected = {})
        }
    }
}
