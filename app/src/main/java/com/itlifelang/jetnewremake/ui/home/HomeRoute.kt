package com.itlifelang.jetnewremake.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.itlifelang.jetnewremake.model.Post

@Composable
fun HomeRoute(
    isExpandScreen: Boolean,
    onOpenDrawer: () -> Unit,
    onNavigateToArticle: (Post) -> Unit
) {
    if (!isExpandScreen) {
        HomeScreen(onOpenDrawer = onOpenDrawer, onNavigateToArticle = onNavigateToArticle)
    } else {
        HomeLargeScreen()
    }
}
