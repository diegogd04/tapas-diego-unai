package com.example.tapas_diego_unai.data

import com.example.tapas_diego_unai.data.local.AppetizerLocalDataSource
import com.example.tapas_diego_unai.data.remote.AppetizerRemoteDataSource
import com.example.tapas_diego_unai.domain.Appetizer
import com.example.tapas_diego_unai.domain.AppetizerRepository
import com.iesam.kotlintrainning.Either
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class AppetizerDataRepository(
    private val local: AppetizerLocalDataSource,
    private val remote: AppetizerRemoteDataSource
): AppetizerRepository {
    override fun obtainAppetizer(): Either<ErrorApp, Appetizer> {
        var appetizer = local.getAppetizer()
        appetizer.mapLeft { errorApp ->
            return remote.getAppetizer().map { appetizer ->
                local.save(appetizer)
                appetizer
            }
        }
        return appetizer
    }
}