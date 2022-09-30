package com.itlifelang.jetnewremake.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.itlifelang.jetnewremake.ui.article.ArticleScreen
import com.itlifelang.jetnewremake.ui.home.HomeRoute
import com.itlifelang.jetnewremake.ui.interest.InterestScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = Navigation.Home.route,
    modifier: Modifier = Modifier,
    isExpandScreen: Boolean,
    onOpenDrawer: () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph(
            navHostController = navHostController,
            isExpandScreen = isExpandScreen,
            onOpenDrawer = onOpenDrawer
        )
        navigation(startDestination = Screen.Interest.route, route = Navigation.Interest.route) {
            composable(route = Screen.Interest.route) {
                InterestScreen(isExpandScreen = isExpandScreen, onOpenDrawer = onOpenDrawer)
            }
        }
    }
}

fun NavGraphBuilder.homeGraph(
    navHostController: NavHostController,
    isExpandScreen: Boolean,
    onOpenDrawer: () -> Unit
) {
    navigation(startDestination = Screen.Home.route, route = Navigation.Home.route) {
        composable(Screen.Home.route) {
            HomeRoute(
                isExpandScreen = isExpandScreen,
                onOpenDrawer = onOpenDrawer,
                onNavigateToArticle = {
                    val route = Screen.Article.withArgs(it.id, it.publication?.name.orEmpty())
                    navHostController.navigate(route)
                }
            )
        }
        composable(
            route = Screen.Article.route + "/{postId}/{publicationName}",
            arguments = listOf(
                navArgument("postId") { type = NavType.StringType },
                navArgument("publicationName") { type = NavType.StringType }
            )
        ) {
            ArticleScreen(
                isExpandScreen = isExpandScreen,
                onBack = { navHostController.popBackStack() }
            )
        }
    }
}
