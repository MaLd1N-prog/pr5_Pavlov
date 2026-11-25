package com.example.pr5_pavlov.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pr5_pavlov.ui.theme.screens.DetailScreen
import com.example.pr5_pavlov.ui.theme.screens.ListScreen

object Routes {
    const val LIST_SCREEN = "list_screen"
    const val DETAIL_SCREEN = "detail_screen/{specialtyId}"

    fun detailRoute(id: Int) = "detail_screen/$id"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LIST_SCREEN
    ) {
        composable(Routes.LIST_SCREEN) {
            ListScreen(
                onSpecialtyClick = { id -> navController.navigate(Routes.detailRoute(id)) },
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme
            )
        }
        composable(
            route = Routes.DETAIL_SCREEN,
            arguments = listOf(navArgument("specialtyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("specialtyId") ?: 0
            DetailScreen(specialtyId = id, onBack = { navController.popBackStack() })
        }
    }
}