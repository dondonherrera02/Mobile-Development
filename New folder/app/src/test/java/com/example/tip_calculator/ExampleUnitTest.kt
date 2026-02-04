package com.example.tip_calculator

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 50.00
        val tipPercent = 20.0
        val expectedTip = "$10.00"
        val actualTip = CalculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_20PercentRoundUp() {
        val amount = 50.00
        val tipPercent = 20.0
        val expectedTip = "$10.00"
        val actualTip = CalculateTip(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_18PercentNoRoundUp() {
        val amount = 100.00
        val tipPercent = 18.0
        val expectedTip = "$18.00"
        val actualTip = CalculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_18PercentRoundUp() {
        val amount = 99.00
        val tipPercent = 18.0
        val expectedTip = "$18.00"
        val actualTip = CalculateTip(amount, tipPercent, true)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_zeroAmount() {
        val amount = 0.00
        val tipPercent = 15.0
        val expectedTip = "$0.00"
        val actualTip = CalculateTip(amount, tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }

    @Test
    fun calculateTip_defaultTipPercent() {
        val amount = 100.00
        val expectedTip = "$15.00"
        val actualTip = CalculateTip(amount, roundUp = false)
        assertEquals(expectedTip, actualTip)
    }
}