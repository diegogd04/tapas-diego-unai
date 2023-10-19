package com.example.tapas_diego_unai.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapas_diego_unai.domain.Appetizer
import com.example.tapas_diego_unai.domain.GetAppetizerUseCase
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AppetizerViewModel(private val getAppetizerUseCase: GetAppetizerUseCase): ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<AppetizerViewModel.UiState> = _uiState

    fun loadAppetizer(){
        _uiState.value = UiState(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            delay(5000)
            getAppetizerUseCase().fold(
                {responseError(it)},
                {responseGetAppetizerSuccess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp){
        _uiState.postValue(UiState(errorApp = errorApp))
    }

    private fun responseGetAppetizerSuccess(appetizer: Appetizer){
        _uiState.postValue(UiState(appetizer = appetizer))
    }

    data class UiState(
        val isLoading: Boolean = false,
        val errorApp: ErrorApp? = null,
        val appetizer: Appetizer? = null
    )
}