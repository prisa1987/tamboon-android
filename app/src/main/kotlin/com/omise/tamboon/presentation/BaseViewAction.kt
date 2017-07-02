package com.omise.tamboon.presentation

interface BaseViewAction {

    fun alertError(title: String = "Error !!", message: String)
    fun onBack()

}