package com.example.calculator.domain.usecase

import com.example.calculator.domain.models.CalculatorState

class CalculateResultUseCase(private val state: CalculatorState) {

    fun execute() {
        if (state.currentNumber.isNotEmpty() && state.operator.isNotEmpty()) {
            calculateIntermediateResult()
            state.operator = ""
        }
    }

    private fun calculateIntermediateResult() {
        val secondNumber = state.currentNumber.toDouble()
        val result = when (state.operator) {
            "+" -> state.firstNumber + secondNumber
            "-" -> state.firstNumber - secondNumber
            "*" -> state.firstNumber * secondNumber
            "/" -> state.firstNumber / secondNumber
            else -> 0.0
        }
        state.firstNumber = result
        state.currentNumber = result.toString()
        state.isOperatorClicked = true
    }

}