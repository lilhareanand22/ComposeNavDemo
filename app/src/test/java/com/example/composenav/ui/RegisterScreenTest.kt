package com.example.composenav.ui

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import com.example.composenav.screens.auth.RegisterScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RegisterScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registerScreen_rendersExpectedElements() {
        composeTestRule.setContent {
            RegisterScreen(
                onRegistered = {},
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Register").assertExists()
        composeTestRule.onNodeWithText("📝 Create Account").assertExists()
        composeTestRule.onNodeWithText("Auth Graph → Register Route").assertExists()
        composeTestRule.onNode(hasText("Full Name")).assertExists()
        composeTestRule.onNode(hasText("Email")).assertExists()
        composeTestRule.onNode(hasText("Password")).assertExists()
        composeTestRule.onNodeWithText("Create Account").assertExists()
    }

    @Test
    fun registerScreen_allowsTextEntryAndTriggersRegistered() {
        var registeredClicked = false
        composeTestRule.setContent {
            RegisterScreen(
                onRegistered = { registeredClicked = true },
                onBack = {},
            )
        }

        composeTestRule.onNode(hasText("Full Name") and hasSetTextAction()).performTextInput("Jane Doe")
        composeTestRule.onNode(hasText("Email") and hasSetTextAction()).performTextInput("jane@example.com")
        composeTestRule.onNode(hasText("Password") and hasSetTextAction()).performTextInput("pass456")

        composeTestRule.onNodeWithText("Create Account").performScrollTo().performClick()

        assertTrue(registeredClicked)
    }

    @Test
    fun registerScreen_triggersBack() {
        var backClicked = false
        composeTestRule.setContent {
            RegisterScreen(
                onRegistered = {},
                onBack = { backClicked = true },
            )
        }

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        assertTrue(backClicked)
    }
}
