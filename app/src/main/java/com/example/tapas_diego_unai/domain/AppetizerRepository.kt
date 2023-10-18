package com.example.tapas_diego_unai.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

interface AppetizerRepository {

    fun obtainAppetizer():Either<ErrorApp, Appetizer>

}