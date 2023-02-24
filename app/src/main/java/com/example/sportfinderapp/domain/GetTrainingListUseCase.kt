package com.example.sportfinderapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetTrainingListUseCase @Inject constructor(private val repository: TrainingRepository) {

    operator fun invoke(): LiveData<List<Training>>{
        return repository.getTrainingList()
    }
}