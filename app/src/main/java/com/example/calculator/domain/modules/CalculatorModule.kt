package com.example.calculator.domain.modules

import com.example.calculator.domain.models.CalculatorState
import com.example.calculator.domain.usecase.CalculateResultUseCase
import com.example.calculator.domain.usecase.ClearUseCase
import com.example.calculator.domain.usecase.NumberClickedUseCase
import com.example.calculator.domain.usecase.OperatorClickedUseCase
import com.example.calculator.domain.usecase.PercentageUseCase
import com.example.calculator.domain.usecase.ToggleSignUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object CalculatorModule {

    @Provides
    @ActivityRetainedScoped
    fun provideCalculatorState(): CalculatorState = CalculatorState()

    @Provides
    fun provideNumberClickedUseCase(calculatorState: CalculatorState): NumberClickedUseCase {
        return NumberClickedUseCase(calculatorState)
    }

    @Provides
    fun provideOperatorClickedUseCase(calculatorState: CalculatorState): OperatorClickedUseCase {
        return OperatorClickedUseCase(calculatorState)
    }

    @Provides
    fun provideCalculateResultUseCase(calculatorState: CalculatorState): CalculateResultUseCase {
        return CalculateResultUseCase(calculatorState)
    }

    @Provides
    fun provideClearUseCase(calculatorState: CalculatorState): ClearUseCase {
        return ClearUseCase(calculatorState)
    }

    @Provides
    fun provideToggleSignUseCase(calculatorState: CalculatorState): ToggleSignUseCase {
        return ToggleSignUseCase(calculatorState)
    }

    @Provides
    fun providePercentageUseCase(calculatorState: CalculatorState): PercentageUseCase {
        return PercentageUseCase(calculatorState)
    }
}