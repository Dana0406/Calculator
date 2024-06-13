package com.example.calculator.presentation

object Constants {

    private val SYMBOLS_ARRAY_INTERNAL = arrayOf("C", "+/-", "%", "/", "7", "8", "9",
        "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", ".", "=")

    val SYMBOLS_ARRAY: Array<String>
        get() = SYMBOLS_ARRAY_INTERNAL.clone()

   val MAX_TEXT_LENGTH = 10
}