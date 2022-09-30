package com.itlifelang.jetnewremake.ui.navigation

import androidx.annotation.StringRes
import com.itlifelang.jetnewremake.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen(route = "home", resourceId = R.string.home)

    object Interest : Screen(route = "interest", resourceId = R.string.interest)

    object Article : Screen(route = "Article", resourceId = R.string.article)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { append("/$it") }
        }
    }
}

sealed class Navigation(val route: String) {
    object Home : Navigation("home_navigation")

    object Interest : Navigation("interest_navigation")
}
