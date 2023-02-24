package com.example.sportfinderapp.domain

import androidx.lifecycle.LiveData

interface TrainingRepository {

    fun addTrainingUseCase(training: Training)

    fun getTrainingUseCase(id: String): Training?

    fun removeTrainingUseCase(id: String)

    fun getTrainingList(): LiveData<List<Training>>
}