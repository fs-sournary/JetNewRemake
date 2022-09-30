package com.itlifelang.jetnewremake.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.itlifelang.jetnewremake.ui.article.ArticleContent

@Composable
fun ArticleNavGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Article.route,
    modifier: Modifier = Modifier,
    postId: String,
    publicationName: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = Screen.Article.route,
            arguments = listOf(
                navArgument("postId") {
                    type = NavType.StringType
                    defaultValue = postId
                },
                navArgument("publicationName") {
                    type = NavType.StringType
                    defaultValue = publicationName
                }
            )
        ) {
            ArticleContent()
        }
    }
}
