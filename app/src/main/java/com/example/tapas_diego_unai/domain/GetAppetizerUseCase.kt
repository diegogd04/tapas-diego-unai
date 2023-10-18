package com.example.tapas_diego_unai.domain

import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class GetAppetizerUseCase(private val repository: AppetizerRepository){
    operator fun invoke():Either<ErrorApp, Appetizer>{
        return repository.obtainAppetizer()
    }
}