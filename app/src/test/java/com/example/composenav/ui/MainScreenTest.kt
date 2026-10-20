package com.example.composenav.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composenav.screens.main.MainScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainScreen_rendersBottomNavigationAndSwitchesTabs() {
        composeTestRule.setContent {
            MainScreen()
        }

        // Verify bottom nav items
        composeTestRule.onNodeWithContentDescription("Home", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithContentDescription("Explore", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithContentDescription("Profile", useUnmergedTree = true).assertExists()

        // Home screen displayed initially
        composeTestRule.onNodeWithText("🏠 Home").assertExists()

        // Click Explore tab
        composeTestRule.onNodeWithContentDescription("Explore", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("🔍 Explore").assertExists()

        // Click Profile tab
        composeTestRule.onNodeWithContentDescription("Profile", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("👤 Profile").assertExists()

        // Return to Home tab
        composeTestRule.onNodeWithContentDescription("Home", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithText("🏠 Home").assertExists()
    }
}
