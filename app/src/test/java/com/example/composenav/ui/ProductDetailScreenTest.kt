package com.example.composenav.ui

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composenav.screens.main.InfoRow
import com.example.composenav.screens.main.ProductDetailScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ProductDetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productDetailScreen_rendersProductDetails() {
        composeTestRule.setContent {
            ProductDetailScreen(
                productId = 101,
                productName = "Awesome Gadget",
                onBack = {},
            )
        }

        composeTestRule.onAllNodesWithText("Awesome Gadget").assertCountEquals(3)
        composeTestRule.onNodeWithText("#101").assertIsDisplayed()
        composeTestRule.onNodeWithText("Type-Safe @Serializable").assertIsDisplayed()
        composeTestRule.onNodeWithText("Passed via data class").assertIsDisplayed()
    }

    @Test
    fun productDetailScreen_backButtonClickTriggersOnBack() {
        var backClicked = false
        composeTestRule.setContent {
            ProductDetailScreen(
                productId = 1,
                productName = "Test",
                onBack = { backClicked = true },
            )
        }

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        assertTrue(backClicked)
    }

    @Test
    fun infoRow_rendersLabelAndValue() {
        composeTestRule.setContent {
            InfoRow(label = "Custom Label", value = "Custom Value")
        }

        composeTestRule.onNodeWithText("Custom Label").assertIsDisplayed()
        composeTestRule.onNodeWithText("Custom Value").assertIsDisplayed()
    }
}
