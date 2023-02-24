package com.example.sportfinderapp.domain

import javax.inject.Inject

class GetTrainingUseCase @Inject constructor(private val repository: TrainingRepository) {

    operator fun invoke(id: String): Training? {
        return repository.getTrainingUseCase(id)
    }
}
