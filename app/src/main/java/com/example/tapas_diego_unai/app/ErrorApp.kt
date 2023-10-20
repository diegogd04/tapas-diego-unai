package com.ugdgomezdiez.androidtraining.app

sealed class ErrorApp {
    object DataErrorApp: ErrorApp()
    object InternetConnectionErrorApp: ErrorApp()
    object UnknowErrorApp: ErrorApp()
}