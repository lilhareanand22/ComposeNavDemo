package com.example.composenav.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.composenav.screens.main.ExploreScreen
import com.example.composenav.screens.main.HomeScreen
import com.example.composenav.screens.main.ProductDetailScreen
import com.example.composenav.screens.main.ProfileScreen
import com.example.composenav.screens.main.SettingsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onProductClick = { id, name ->
                    navController.navigate(ProductDetailRoute(id, name))
                },
            )
        }

        composable<ExploreRoute> {
            ExploreScreen()
        }

        composable<ProfileRoute> {
            ProfileScreen(
                onGoToSettings = { navController.navigate(SettingsRoute) },
            )
        }

        composable<ProductDetailRoute> { backStackEntry ->
            val route: ProductDetailRoute = backStackEntry.toRoute()
            ProductDetailScreen(
                productId = route.productId,
                productName = route.productName,
                onBack = { navController.popBackStack() },
            )
        }

        composable<SettingsRoute> {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
