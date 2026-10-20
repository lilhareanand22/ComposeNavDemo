package com.example.composenav.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.composenav.screens.main.sampleProducts
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainNavGraphTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mainNavGraph_navigatesToProductDetailAndBack() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            MainNavGraph(navController = navController)
        }

        // Home screen displayed
        composeTestRule.onNodeWithText("🏠 Home").assertExists()

        // Click first product
        val targetProduct = sampleProducts.first()
        composeTestRule.onNodeWithText(targetProduct.name).performClick()

        // Navigated to ProductDetailScreen
        composeTestRule.onNodeWithText("#${targetProduct.id}").assertExists()

        // Click Back icon
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Returned to Home screen
        composeTestRule.onNodeWithText("🏠 Home").assertExists()
    }

    @Test
    fun mainNavGraph_navigatesFromProfileToSettingsAndBack() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            MainNavGraph(navController = navController)
            navController.navigate(ProfileRoute)
        }

        // Profile screen displayed
        composeTestRule.onNodeWithText("John Doe").assertExists()

        // Click Settings icon
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        // Settings screen displayed
        composeTestRule.onNodeWithText("⚙️ Settings").assertExists()

        // Click Back icon
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Returned to Profile screen
        composeTestRule.onNodeWithText("John Doe").assertExists()
    }
}
