package com.example.tapas_diego_unai.data.remote

import com.example.tapas_diego_unai.domain.Appetizer
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class AppetizerRemoteDataSource {
    fun getAppetizer(): Either<ErrorApp, Appetizer> =
        Appetizer(
            "https://gourmetabulense.files.wordpress.com/2021/06/26-vermuteria-el-atrio-albondiga-de-faisan-en-escabeche-con-caldo-clarificado-de-gambon-salvaje.jpg?w=740",
            "1º",
            "Albóndiga de faisán en escabeche con caldo clarificado de gambón salvaje",
            "Vermuteria el Atrio",
            0,
            0.0
        ).right()
}