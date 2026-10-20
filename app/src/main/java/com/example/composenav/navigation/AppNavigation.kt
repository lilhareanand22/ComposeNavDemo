package com.example.composenav.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.composenav.screens.auth.LoginScreen
import com.example.composenav.screens.auth.RegisterScreen
import com.example.composenav.screens.main.MainScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<NavKey> = rememberNavBackStack(LoginRoute),
) {
    val entryProvider =
        entryProvider {
            entry<LoginRoute> {
                LoginScreen(
                    onLoginSuccess = {
                        backStack.clear()
                        backStack.add(MainGraph)
                    },
                    onGoToRegister = {
                        backStack.add(RegisterRoute)
                    },
                )
            }

            entry<RegisterRoute> {
                RegisterScreen(
                    onRegistered = {
                        backStack.clear()
                        backStack.add(MainGraph)
                    },
                    onBack = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    },
                )
            }

            entry<MainGraph> {
                MainScreen()
            }
        }

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        entryProvider = entryProvider,
    )
}
