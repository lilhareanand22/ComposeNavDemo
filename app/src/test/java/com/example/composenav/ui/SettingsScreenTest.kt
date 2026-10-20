package com.example.composenav.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composenav.screens.main.SettingsScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SettingsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingsScreen_rendersOptionsAndToggles() {
        composeTestRule.setContent {
            SettingsScreen(onBack = {})
        }

        composeTestRule.onNodeWithText("⚙️ Settings").assertIsDisplayed()
        composeTestRule.onNodeWithText("Main Graph → SettingsRoute").assertIsDisplayed()
        composeTestRule.onNodeWithText("Push Notifications").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dark Mode").assertIsDisplayed()

        // Test toggling switches
        composeTestRule.onNodeWithText("Push Notifications").performClick()
        composeTestRule.onNodeWithText("Dark Mode").performClick()
    }

    @Test
    fun settingsScreen_backButtonClickTriggersOnBack() {
        var backClicked = false
        composeTestRule.setContent {
            SettingsScreen(onBack = { backClicked = true })
        }

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        assertTrue(backClicked)
    }
}
