package com.example.sportfinderapp.domain.usecases.sport

import com.example.sportfinderapp.domain.repository.SportRepository
import com.example.sportfinderapp.domain.entity.Sport
import javax.inject.Inject

class AddSportUseCase @Inject constructor(private val repository: SportRepository) {
    operator fun invoke(sport: Sport) {
        repository.addSportUseCase(sport)
    }
}