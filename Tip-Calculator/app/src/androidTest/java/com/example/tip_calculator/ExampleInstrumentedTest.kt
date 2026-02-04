package com.example.tip_calculator

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tip_calculator.ui.theme.TipCalculatorTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TipCalculatorUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator(name = "Android")
            }
        }

        // Enter bill amount
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("50")

        // Enter tip percentage
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")

        // Check that tip is calculated correctly
        composeTestRule.onNodeWithText("Tip Amount: $10.00")
            .assertIsDisplayed()
    }

    @Test
    fun calculate_tip_with_rounding() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator(name = "Android")
            }
        }

        // Enter bill amount
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("99")

        // Enter tip percentage
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("18")

        // Toggle round up switch
        composeTestRule.onNodeWithText("Round up tip?")
            .performClick()

        // Check that tip is rounded up
        composeTestRule.onNodeWithText("Tip Amount: $18.00")
            .assertIsDisplayed()
    }

    @Test
    fun initial_tip_amount_is_zero() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator(name = "Android")
            }
        }

        // Check that initial tip is $0.00
        composeTestRule.onNodeWithText("Tip Amount: $0.00")
            .assertIsDisplayed()
    }

    @Test
    fun title_is_displayed() {
        composeTestRule.setContent {
            TipCalculatorTheme {
                TipCalculator(name = "Android")
            }
        }

        // Check that title is displayed
        composeTestRule.onNodeWithText("Calculator Tip")
            .assertIsDisplayed()
    }
}