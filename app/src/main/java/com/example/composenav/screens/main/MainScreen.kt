package com.example.composenav.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.composenav.navigation.ExploreRoute
import com.example.composenav.navigation.HomeRoute
import com.example.composenav.navigation.MainNavGraph
import com.example.composenav.navigation.ProfileRoute
import com.example.composenav.navigation.bottomNavItems

@Composable
fun MainScreen(
    homeBackStack: NavBackStack<NavKey> = rememberNavBackStack(HomeRoute),
    exploreBackStack: NavBackStack<NavKey> = rememberNavBackStack(ExploreRoute),
    profileBackStack: NavBackStack<NavKey> = rememberNavBackStack(ProfileRoute),
) {
    var selectedTabLabel by rememberSaveable { mutableStateOf("Home") }

    val currentBackStack =
        when (selectedTabLabel) {
            "Home" -> homeBackStack
            "Explore" -> exploreBackStack
            "Profile" -> profileBackStack
            else -> homeBackStack
        }

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    val selected = item.label == selectedTabLabel

                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selected,
                        onClick = {
                            if (selectedTabLabel == item.label) {
                                if (currentBackStack.size > 1) {
                                    currentBackStack.clear()
                                    currentBackStack.add(item.route)
                                }
                            } else {
                                selectedTabLabel = item.label
                            }
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        MainNavGraph(
            currentBackStack = currentBackStack,
            modifier = Modifier.padding(innerPadding),
        )
    }
}
