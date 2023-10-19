package com.example.tapas_diego_unai.data.remote

import com.example.tapas_diego_unai.domain.Appetizer
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class AppetizerRemoteDataSource {
    fun getAppetizer(): Either<ErrorApp, Appetizer> =
        Appetizer(
            "1º",
            "Albóndiga de faisán en escabeche con caldo clarificado de gambón salvaje",
            "Vermuteria el Atrio",
            0,
            0.0
        ).right()
}