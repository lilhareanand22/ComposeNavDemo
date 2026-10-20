package com.example.composenav.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.composenav.screens.auth.LoginScreen
import com.example.composenav.screens.auth.RegisterScreen
import com.example.composenav.screens.main.MainScreen

@Composable
fun AppNavigation() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = AuthGraph
    ) {
        // ── Nested Auth Graph ──────────────────────────────────
        navigation<AuthGraph>(startDestination = LoginRoute) {

            composable<LoginRoute> {
                LoginScreen(
                    onLoginSuccess = {
                        rootNavController.navigate(MainGraph) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    },
                    onGoToRegister = {
                        rootNavController.navigate(RegisterRoute)
                    }
                )
            }

            composable<RegisterRoute> {
                RegisterScreen(
                    onRegistered = {
                        rootNavController.navigate(MainGraph) {
                            popUpTo(AuthGraph) { inclusive = true }
                        }
                    },
                    onBack = { rootNavController.popBackStack() }
                )
            }
        }

        // ── Nested Main Graph ──────────────────────────────────
        composable<MainGraph> {
            MainScreen()
        }
    }
}
