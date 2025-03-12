package com.example.getshitdone

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.getshitdone.ui.theme.GetShitDoneTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun miscInstrumentedTests() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val appName = appContext.getText(R.string.app_name)
        assertEquals(appName, "GetShitDone")
        assertEquals("com.example.getshitdone", appContext.packageName)
    }

    @Test
    fun showDefaultElements() {
        composeTestRule.setContent {
            GetShitDoneTheme {
                GetShitDoneApp()
            }
        }

        composeTestRule.onNodeWithText("Create a TODO").assertIsDisplayed()
        composeTestRule.onNodeWithText("No Todos").assertIsDisplayed()
    }
}