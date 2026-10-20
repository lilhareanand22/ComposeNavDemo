package com.example.composenav.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composenav.screens.main.ProfileScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProfileScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun profileScreen_rendersProfileInfo() {
        composeTestRule.setContent {
            ProfileScreen(onGoToSettings = {})
        }

        composeTestRule.onNodeWithText("👤 Profile").assertIsDisplayed()
        composeTestRule.onNodeWithText("John Doe").assertIsDisplayed()
        composeTestRule.onNodeWithText("john@example.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("Main Graph → ProfileRoute").assertIsDisplayed()
        composeTestRule.onNodeWithText("💡 Tips:").assertIsDisplayed()
    }

    @Test
    fun profileScreen_settingsIconClickTriggersOnGoToSettings() {
        var settingsClicked = false
        composeTestRule.setContent {
            ProfileScreen(onGoToSettings = { settingsClicked = true })
        }

        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        assertTrue(settingsClicked)
    }
}
