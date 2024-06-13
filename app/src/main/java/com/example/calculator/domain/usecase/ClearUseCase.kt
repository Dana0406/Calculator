package com.example.calculator.domain.usecase

import com.example.calculator.domain.models.CalculatorState

class ClearUseCase(private val state: CalculatorState) {

    fun execute() {
        state.clear()
    }

}