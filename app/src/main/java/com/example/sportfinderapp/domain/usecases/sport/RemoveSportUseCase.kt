package com.example.sportfinderapp.domain.usecases.sport

import com.example.sportfinderapp.domain.repository.SportRepository
import javax.inject.Inject

class RemoveSportUseCase @Inject constructor(private val repository: SportRepository) {

    operator fun invoke(id: Int){
        repository.removeSportUseCase(id)
    }
}