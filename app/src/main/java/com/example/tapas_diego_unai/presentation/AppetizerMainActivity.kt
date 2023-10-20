package com.example.tapas_diego_unai.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.tapas_diego_unai.R
import com.example.tapas_diego_unai.app.serialization.GsonSerialization
import com.example.tapas_diego_unai.data.AppetizerDataRepository
import com.example.tapas_diego_unai.data.local.AppetizerLocalDataSource
import com.example.tapas_diego_unai.data.remote.AppetizerRemoteDataSource
import com.example.tapas_diego_unai.databinding.ActivityMainBinding
import com.example.tapas_diego_unai.domain.Appetizer
import com.example.tapas_diego_unai.domain.GetAppetizerUseCase
import com.faltenreich.skeletonlayout.Skeleton
import com.google.android.material.snackbar.Snackbar
import com.ugdgomezdiez.androidtraining.app.ErrorApp
import com.ugdgomezdiez.androidtraining.app.extensions.setUrl
import java.lang.Error

class AppetizerMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: AppetizerViewModel by lazy {
        AppetizerViewModel(
            GetAppetizerUseCase(
                AppetizerDataRepository(
                    AppetizerLocalDataSource(
                        this,
                        GsonSerialization()
                    ), AppetizerRemoteDataSource()
                )
            )
        )
    }
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBinding()
        setupView()
        setupObserver()
    }

    private fun setupBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView(){
        skeleton = binding.layoutSkeleton
    }

    private fun setupObserver(){
        val observer=Observer<AppetizerViewModel.UiState>{
            if (it.isLoading){
                showLoading()
            }else{
                hideLoading()
            }

            it.errorApp?.let{
                showError(it)
            }

            it.appetizer?.let {
                bindData(it)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

   private fun showError(error: ErrorApp){
        Snackbar.make(
            binding.root,
            getString(R.string.label_error),
            Snackbar.LENGTH_SHORT
        ).show()
    }
    private fun showLoading(){
        skeleton.showSkeleton()
    }

    private fun hideLoading(){
        skeleton.showOriginal()
    }

    private fun bindData(appetizer: Appetizer){
        binding.apply {
            imagen.setUrl(appetizer.image)
            puesto.text = appetizer.ranking
            name.text = appetizer.name
            location.text = appetizer.location
            puntosTotales.text = appetizer.totalPoints.toString()
            puntosMedia.text = appetizer.averagePoints.toString()
        }
    }
}