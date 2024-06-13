package com.example.calculator.domain.usecase

import com.example.calculator.domain.models.CalculatorState

class OperatorClickedUseCase(private val state: CalculatorState) {

    fun execute(newOperator: String) {
        if (state.currentNumber.isNotEmpty()) {
            if (state.operator.isNotEmpty()) {
                calculateIntermediateResult()
            } else {
                state.firstNumber = state.currentNumber.toDouble()
            }
            state.operator = newOperator
            state.isOperatorClicked = true
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