package com.example.sportfinderapp.domain

import javax.inject.Inject

class RemoveTrainingUseCase @Inject constructor(private val repository: TrainingRepository) {

    operator fun invoke(id: String){
        repository.removeTrainingUseCase(id)
    }
}