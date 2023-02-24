package com.example.sportfinderapp.domain

import javax.inject.Inject

class AddTrainingUseCase @Inject constructor(private val repository: TrainingRepository) {
    operator fun invoke(training: Training) {
        repository.addTrainingUseCase(training)
    }
}