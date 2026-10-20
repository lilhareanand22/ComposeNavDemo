package com.example.composenav.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AppNavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun appNavigation_startsAtLogin_navigatesToRegisterAndBack() {
        composeTestRule.setContent {
            AppNavigation()
        }

        // Starts at Login
        composeTestRule.onNodeWithText("🔑 Login").assertExists()

        // Click Register link
        composeTestRule.onNodeWithText("Don't have an account? Register").performClick()

        // Navigates to Register
        composeTestRule.onNodeWithText("📝 Create Account").assertExists()

        // Press back arrow
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Returns to Login
        composeTestRule.onNodeWithText("🔑 Login").assertExists()
    }

    @Test
    fun appNavigation_loginSuccess_navigatesToMainGraph() {
        composeTestRule.setContent {
            AppNavigation()
        }

        composeTestRule.onNodeWithText("Login (any credentials work)").performClick()

        // MainGraph loaded with Home screen
        composeTestRule.onNodeWithText("🏠 Home").assertExists()
    }

    @Test
    fun appNavigation_registerAccount_navigatesToMainGraph() {
        composeTestRule.setContent {
            AppNavigation()
        }

        composeTestRule.onNodeWithText("Don't have an account? Register").performClick()
        composeTestRule.onNodeWithText("Create Account").performScrollTo().performClick()

        // MainGraph loaded
        composeTestRule.onNodeWithText("🏠 Home").assertExists()
    }

    @Test
    fun appNavigation_backStackStateOperations() {
        lateinit var backStack: MutableList<NavKey>
        composeTestRule.setContent {
            backStack = rememberNavBackStack(LoginRoute)
        }

        assertEquals(1, backStack.size)
        assertEquals(LoginRoute, backStack.last())

        backStack.add(RegisterRoute)
        assertEquals(2, backStack.size)
        assertEquals(RegisterRoute, backStack.last())

        backStack.removeAt(backStack.lastIndex)
        assertEquals(1, backStack.size)
        assertEquals(LoginRoute, backStack.last())

        backStack.clear()
        backStack.add(MainGraph)
        assertEquals(1, backStack.size)
        assertEquals(MainGraph, backStack.last())
    }
}
