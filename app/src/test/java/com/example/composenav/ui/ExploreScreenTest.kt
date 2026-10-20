package com.example.composenav.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.composenav.screens.main.ExploreScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ExploreScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun exploreScreen_rendersElements() {
        composeTestRule.setContent {
            ExploreScreen()
        }

        composeTestRule.onNodeWithText("🔍 Explore").assertIsDisplayed()
        composeTestRule.onNodeWithText("Explore Screen").assertIsDisplayed()
        composeTestRule.onNodeWithText("Main Graph → ExploreRoute").assertIsDisplayed()
        composeTestRule.onNodeWithText("💡 Bottom Navigation Concepts:").assertIsDisplayed()
        composeTestRule.onNodeWithText("• launchSingleTop = true — no duplicate screens").assertIsDisplayed()
    }
}
