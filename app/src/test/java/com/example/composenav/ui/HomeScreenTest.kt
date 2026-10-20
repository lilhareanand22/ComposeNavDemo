package com.example.composenav.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.composenav.screens.main.HomeScreen
import com.example.composenav.screens.main.sampleProducts
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_rendersTitleAndSampleProducts() {
        composeTestRule.setContent {
            HomeScreen(onProductClick = { _, _ -> })
        }

        composeTestRule.onNodeWithText("🏠 Home").assertExists()
        composeTestRule.onNodeWithText("Main Graph → Home Route").assertExists()
        composeTestRule.onNodeWithText("Tap a product to navigate with arguments →").assertExists()

        for (product in sampleProducts.take(3)) {
            composeTestRule.onNodeWithText(product.name, useUnmergedTree = true).assertExists()
            composeTestRule.onNodeWithText(product.description, useUnmergedTree = true).assertExists()
        }
    }

    @Test
    fun homeScreen_triggersProductClick() {
        var clickedId = -1
        var clickedName = ""

        composeTestRule.setContent {
            HomeScreen(
                onProductClick = { id, name ->
                    clickedId = id
                    clickedName = name
                },
            )
        }

        val targetProduct = sampleProducts.first()
        composeTestRule.onNodeWithText(targetProduct.name).performClick()

        assertEquals(targetProduct.id, clickedId)
        assertEquals(targetProduct.name, clickedName)
    }
}
