package com.example.githubaction

import androidx.lifecycle.ViewModel
import com.example.githubaction.calculator.ICalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculator: ICalculator // 1. Inject the ICalculator interface
) : ViewModel() {

    fun performAddition(a: Int, b: Int): Int {
        return calculator.add(a, b)
    }

    fun performSubtraction(a: Int, b: Int): Int {
        return calculator.subtract(a, b)
    }

}