package com.example.calculator.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.domain.models.CalculatorState
import com.example.calculator.domain.usecase.CalculateResultUseCase
import com.example.calculator.domain.usecase.ClearUseCase
import com.example.calculator.domain.usecase.NumberClickedUseCase
import com.example.calculator.domain.usecase.OperatorClickedUseCase
import com.example.calculator.domain.usecase.PercentageUseCase
import com.example.calculator.domain.usecase.ToggleSignUseCase
import com.example.calculator.presentation.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculatorState: CalculatorState,
    private val numberClickedUseCase: NumberClickedUseCase,
    private val operatorClickedUseCase: OperatorClickedUseCase,
    private val calculateResultUseCase: CalculateResultUseCase,
    private val clearUseCase: ClearUseCase,
    private val toggleSignUseCase: ToggleSignUseCase,
    private val percentageUseCase: PercentageUseCase
) : ViewModel() {

    private val _solutionText = MutableLiveData<String>()
    val solutionText: LiveData<String>
        get() = _solutionText

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String>
        get() = _resultText

    init {
        updateUI()
    }

    fun onButtonClick(button: String) {
        when (button) {
            "/", "*", "-", "+" -> {
                operatorClickedUseCase.execute(button)
                appendToSolutionText(" $button ")
            }

            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." -> {
                numberClickedUseCase.execute(button)
                appendToSolutionText(button)
                updateResult()
            }

            "=" -> {
                calculateResultUseCase.execute()
                updateSolutionText()
            }

            "C" -> {
                clearUseCase.execute()
                updateUI()
            }

            "+/-" -> {
                toggleSignUseCase.execute()
                updateResult()
                updateSolutionText()
            }

            "%" -> {
                percentageUseCase.execute()
                updatePerResult()
                updateSolutionText()
            }

            else -> {
               Log.d("Error","error")
            }
        }
    }

    private fun appendToSolutionText(value: String) {
        _solutionText.value = "${_solutionText.value}$value"
    }

    private fun updateSolutionText() {
        _solutionText.value = _resultText.value?.let { formatDouble(it.toDouble()) }
    }

    private fun updatePerResult(){
        if(calculatorState.firstNumber == 0.0){
            _resultText.value = calculatorState.currentNumber
        }else{
            updateResult()
        }
    }

    private fun updateResult() {
        if (calculatorState.operator.isNotEmpty() && calculatorState.currentNumber.isNotEmpty()) {
            val secondNumber = calculatorState.currentNumber.toDouble()
            val result = when (calculatorState.operator) {
                "+" -> calculatorState.firstNumber + secondNumber
                "-" -> calculatorState.firstNumber - secondNumber
                "*" -> calculatorState.firstNumber * secondNumber
                "/" -> calculatorState.firstNumber / secondNumber
                else -> 0.0
            }
            _resultText.value = formatDouble(result)
        }
    }

    private fun updateUI() {
        _solutionText.value = ""
        _resultText.value = ""
    }

    private fun formatDouble(value: Double): String {
        val decimalFormat = DecimalFormat("#.#####")
        return decimalFormat.format(value).takeLast(Constants.MAX_TEXT_LENGTH)
    }
}
