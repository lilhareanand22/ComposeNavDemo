package com.example.composenav.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
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
}
