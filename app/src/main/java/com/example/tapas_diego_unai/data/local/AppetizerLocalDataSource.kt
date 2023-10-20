package com.example.tapas_diego_unai.data.local

import android.content.Context
import com.example.tapas_diego_unai.app.serialization.JsonSerialization
import com.example.tapas_diego_unai.domain.Appetizer
import com.iesam.kotlintrainning.Either
import com.iesam.kotlintrainning.left
import com.iesam.kotlintrainning.right
import com.ugdgomezdiez.androidtraining.app.ErrorApp

class AppetizerLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {
    private val sharedPref = context.getSharedPreferences("appetizer", Context.MODE_PRIVATE)
    private val appetizerId = "1"

    fun getAppetizer():Either<ErrorApp, Appetizer>{
        val jsonAppetizer = sharedPref.getString(appetizerId, null)
        jsonAppetizer?.let {
            return serialization.fromJson(it,Appetizer::class.java).right()
        }
        return ErrorApp.DataErrorApp.left()
    }

    fun save(appetizer: Appetizer){
        sharedPref.edit().apply(){
            putString(appetizerId, serialization.toJson(appetizer, Appetizer::class.java))
            apply()
        }
    }
}