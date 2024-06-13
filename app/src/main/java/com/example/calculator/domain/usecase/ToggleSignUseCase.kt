package com.example.calculator.domain.usecase

import com.example.calculator.domain.models.CalculatorState

class ToggleSignUseCase(private val state: CalculatorState) {

    fun execute() {
        if (state.currentNumber.isNotEmpty()) {
            state.currentNumber = (state.currentNumber.toDouble() * -1).toString()
        }
    }

}