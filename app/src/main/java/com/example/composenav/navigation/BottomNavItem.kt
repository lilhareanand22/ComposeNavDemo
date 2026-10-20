package com.example.composenav.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: NavKey,
)

val bottomNavItems =
    listOf(
        BottomNavItem("Home", Icons.Default.Home, HomeRoute),
        BottomNavItem("Explore", Icons.Default.Explore, ExploreRoute),
        BottomNavItem("Profile", Icons.Default.Person, ProfileRoute),
    )
