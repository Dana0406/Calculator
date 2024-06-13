package com.example.calculator.domain.usecase

import com.example.calculator.domain.models.CalculatorState

class NumberClickedUseCase(private val state: CalculatorState) {

    fun execute(number: String) {
        if (state.isOperatorClicked) {
            state.currentNumber = ""
            state.isOperatorClicked = false
        }
        state.currentNumber += number
    }

}