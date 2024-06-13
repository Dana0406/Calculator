package com.example.calculator.domain.models

data class CalculatorState(
    var firstNumber: Double = 0.0,
    var currentNumber: String = "",
    var operator: String = "",
    var isOperatorClicked: Boolean = false
) {
    fun clear() {
        firstNumber = 0.0
        currentNumber = ""
        operator = ""
        isOperatorClicked = false
    }
}