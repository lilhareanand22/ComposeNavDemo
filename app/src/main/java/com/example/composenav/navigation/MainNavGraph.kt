package com.example.composenav.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.composenav.screens.main.ExploreScreen
import com.example.composenav.screens.main.HomeScreen
import com.example.composenav.screens.main.ProductDetailScreen
import com.example.composenav.screens.main.ProfileScreen
import com.example.composenav.screens.main.SettingsScreen

@Composable
fun MainNavGraph(
    currentBackStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
) {
    val entryProvider =
        entryProvider {
            entry<HomeRoute> {
                HomeScreen(
                    onProductClick = { id, name ->
                        currentBackStack.add(ProductDetailRoute(id, name))
                    },
                )
            }

            entry<ExploreRoute> {
                ExploreScreen()
            }

            entry<ProfileRoute> {
                ProfileScreen(
                    onGoToSettings = {
                        currentBackStack.add(SettingsRoute)
                    },
                )
            }

            entry<ProductDetailRoute> { key ->
                ProductDetailScreen(
                    productId = key.productId,
                    productName = key.productName,
                    onBack = {
                        if (currentBackStack.size > 1) {
                            currentBackStack.removeAt(currentBackStack.lastIndex)
                        }
                    },
                )
            }

            entry<SettingsRoute> {
                SettingsScreen(
                    onBack = {
                        if (currentBackStack.size > 1) {
                            currentBackStack.removeAt(currentBackStack.lastIndex)
                        }
                    },
                )
            }
        }

    NavDisplay(
        backStack = currentBackStack,
        modifier = modifier,
        entryProvider = entryProvider,
    )
}
