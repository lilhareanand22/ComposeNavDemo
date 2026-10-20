package com.example.composenav.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.composenav.screens.auth.LoginScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_rendersExpectedElements() {
        composeTestRule.setContent {
            LoginScreen(
                onLoginSuccess = {},
                onGoToRegister = {},
            )
        }

        composeTestRule.onNodeWithText("🔑 Login").assertIsDisplayed()
        composeTestRule.onNodeWithText("Auth Graph → Login Route").assertIsDisplayed()
        composeTestRule.onNode(hasText("Email")).assertIsDisplayed()
        composeTestRule.onNode(hasText("Password")).assertIsDisplayed()
        composeTestRule.onNodeWithText("Login (any credentials work)").assertIsDisplayed()
        composeTestRule.onNodeWithText("Don't have an account? Register").assertIsDisplayed()
    }

    @Test
    fun loginScreen_allowsTextEntryAndTriggersLoginSuccess() {
        var loginClicked = false
        composeTestRule.setContent {
            LoginScreen(
                onLoginSuccess = { loginClicked = true },
                onGoToRegister = {},
            )
        }

        composeTestRule.onNode(hasText("Email") and hasSetTextAction()).performTextInput("test@example.com")
        composeTestRule.onNode(hasText("Password") and hasSetTextAction()).performTextInput("secret123")

        composeTestRule.onNodeWithText("Login (any credentials work)").performClick()

        assertTrue(loginClicked)
    }

    @Test
    fun loginScreen_triggersGoToRegister() {
        var registerClicked = false
        composeTestRule.setContent {
            LoginScreen(
                onLoginSuccess = {},
                onGoToRegister = { registerClicked = true },
            )
        }

        composeTestRule.onNodeWithText("Don't have an account? Register").performClick()

        assertTrue(registerClicked)
    }
}
