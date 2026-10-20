package com.example.composenav.ui

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.composenav.MainActivity
import com.example.composenav.ui.theme.ComposeNavDemoTheme
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ThemeAndActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun composeNavDemoTheme_rendersContent() {
        composeTestRule.setContent {
            ComposeNavDemoTheme {
                Text("Theme Test Content")
            }
        }

        composeTestRule.onNodeWithText("Theme Test Content").assertIsDisplayed()
    }

    @Test
    fun mainActivity_launchesSuccessfully() {
        val controller = Robolectric.buildActivity(MainActivity::class.java)
        controller.setup()
        val activity = controller.get()
        assertNotNull(activity)
    }
}
