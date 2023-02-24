package com.example.sportfinderapp.presentation.fragments.home

import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.data.TrainingRepositoryImpl
import com.example.sportfinderapp.domain.GetTrainingListUseCase
import com.example.sportfinderapp.domain.TrainingRepository

class HomeViewModel : ViewModel() {

    private val repository = TrainingRepositoryImpl()

    private val getTrainingListUseCase = GetTrainingListUseCase(repository)

    val trainingList = getTrainingListUseCase()

}