package com.itlifelang.jetnewremake.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itlifelang.jetnewremake.ui.component.AppDrawer
import com.itlifelang.jetnewremake.ui.component.AppNavRail
import com.itlifelang.jetnewremake.ui.navigation.AppNavGraph
import com.itlifelang.jetnewremake.ui.navigation.Navigation
import com.itlifelang.jetnewremake.ui.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val scope = rememberCoroutineScope()
            val navHostController = rememberNavController()
            val windowSizeClass = calculateWindowSizeClass(activity = this)
            val isExpandScreen = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded
            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route
            val drawerState = if (!isExpandScreen) {
                rememberDrawerState(initialValue = DrawerValue.Closed)
            } else {
                DrawerState(DrawerValue.Closed)
            }
            ModalNavigationDrawer(
                drawerContent = {
                    AppDrawer(
                        currentRoute = currentRoute,
                        navigateToHome = {
                            navigateToScreen(navHostController, Navigation.Home.route)
                        },
                        navigateToInterest = {
                            navigateToScreen(navHostController, Navigation.Interest.route)
                        },
                        closeDrawer = {
                            scope.launch { drawerState.close() }
                        }
                    )
                },
                drawerState = drawerState,
                gesturesEnabled = !isExpandScreen
            ) {
                Row {
                    if (isExpandScreen) {
                        AppNavRail(
                            currentRoute = currentRoute,
                            navigateToHome = {
                                navigateToScreen(navHostController, Navigation.Home.route)
                            },
                            navigateToInterest = {
                                navigateToScreen(navHostController, Navigation.Interest.route)
                            }
                        )
                    }
                    AppNavGraph(
                        navHostController = navHostController,
                        isExpandScreen = isExpandScreen,
                        onOpenDrawer = {
                            scope.launch { drawerState.open() }
                        }
                    )
                }
            }
        }
    }

    private fun navigateToScreen(navHostController: NavHostController, route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
