package com.example.composenav.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.composenav.screens.main.sampleProducts
import org.junit.Assert.assertEquals
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
        lateinit var backStack: MutableList<NavKey>
        composeTestRule.setContent {
            backStack = rememberNavBackStack(HomeRoute)
            MainNavGraph(currentBackStack = backStack)
        }

        // Home screen displayed
        composeTestRule.onNodeWithText("🏠 Home").assertExists()
        assertEquals(1, backStack.size)
        assertEquals(HomeRoute, backStack.last())

        // Click first product
        val targetProduct = sampleProducts.first()
        composeTestRule.onNodeWithText(targetProduct.name).performClick()

        // Navigated to ProductDetailScreen
        composeTestRule.onNodeWithText("#${targetProduct.id}").assertExists()
        assertEquals(2, backStack.size)
        assertEquals(ProductDetailRoute(targetProduct.id, targetProduct.name), backStack.last())

        // Click Back icon
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Returned to Home screen
        composeTestRule.onNodeWithText("🏠 Home").assertExists()
        assertEquals(1, backStack.size)
        assertEquals(HomeRoute, backStack.last())
    }

    @Test
    fun mainNavGraph_navigatesFromProfileToSettingsAndBack() {
        lateinit var backStack: MutableList<NavKey>
        composeTestRule.setContent {
            backStack = rememberNavBackStack(ProfileRoute)
            MainNavGraph(currentBackStack = backStack)
        }

        // Profile screen displayed
        composeTestRule.onNodeWithText("John Doe").assertExists()
        assertEquals(1, backStack.size)
        assertEquals(ProfileRoute, backStack.last())

        // Click Settings icon
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        // Settings screen displayed
        composeTestRule.onNodeWithText("⚙️ Settings").assertExists()
        assertEquals(2, backStack.size)
        assertEquals(SettingsRoute, backStack.last())

        // Click Back icon
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Returned to Profile screen
        composeTestRule.onNodeWithText("John Doe").assertExists()
        assertEquals(1, backStack.size)
        assertEquals(ProfileRoute, backStack.last())
    }
}
