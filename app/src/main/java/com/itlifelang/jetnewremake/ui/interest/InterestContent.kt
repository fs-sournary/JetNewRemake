package com.itlifelang.jetnewremake.ui.interest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InterestContent(modifier: Modifier = Modifier, isExpandScreen: Boolean) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    Column(modifier = modifier) {
        InterestTab(
            isExpandScreen = isExpandScreen,
            selectedTabIndex = pagerState.currentPage
        ) {
            scope.launch { pagerState.scrollToPage(it) }
        }
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
        InterestPager(
            modifier = Modifier.fillMaxSize(),
            pagerState = pagerState,
            isExpandScreen = isExpandScreen
        )
    }
}
