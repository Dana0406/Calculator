package com.example.calculator.domain.usecase

import com.example.calculator.domain.models.CalculatorState

class PercentageUseCase(private val state: CalculatorState) {

    fun execute() {
        if (state.currentNumber.isNotEmpty()) {
            val number = state.currentNumber.toDoubleOrNull() ?: 0.0
            state.currentNumber = (number / 100).toString()
        } else {
            state.currentNumber = "0"
        }
    }
}