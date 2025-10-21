package com.example.githubaction.calculator

import javax.inject.Inject

class CalculatorImpl @Inject constructor() : ICalculator {
    // The @Inject constructor tells Hilt how to create an instance of this class.

    override fun add(a: Int, b: Int): Int {
        return a + b
    }

    override fun subtract(a: Int, b: Int): Int {
        return a - b
    }
}