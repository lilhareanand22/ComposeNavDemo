package com.example.composenav

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composenav.navigation.AppNavigation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppNavigationInstrumentedTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun app_launchesAndNavigatesToMainFlow() {
        // Initially on Login screen
        composeTestRule.onNodeWithText("🔑 Login").assertExists()

        // Click login button
        composeTestRule.onNodeWithText("Login (any credentials work)").performClick()

        // Home screen displayed
        composeTestRule.onNodeWithText("🏠 Home").assertExists()

        // Navigate to Explore tab
        composeTestRule.onNodeWithContentDescription("Explore", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("🔍 Explore").assertExists()

        // Navigate to Profile tab
        composeTestRule.onNodeWithContentDescription("Profile", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("John Doe").assertExists()

        // Open Settings from Profile
        composeTestRule.onNodeWithContentDescription("Settings").performClick()
        composeTestRule.onNodeWithText("⚙️ Settings").assertExists()

        // Go back to Profile
        composeTestRule.onNodeWithContentDescription("Back").performClick()
        composeTestRule.onNodeWithText("John Doe").assertExists()
    }

    @Test
    fun app_registerFlowNavigation() {
        composeTestRule.setContent {
            AppNavigation()
        }

        // Navigate to Register
        composeTestRule.onNodeWithText("Don't have an account? Register").performClick()
        composeTestRule.onNodeWithText("📝 Create Account").assertExists()

        // Click Create Account
        composeTestRule.onNodeWithText("Create Account").performScrollTo().performClick()

        // MainGraph Home screen loaded
        composeTestRule.onNodeWithText("🏠 Home").assertExists()
    }
}
