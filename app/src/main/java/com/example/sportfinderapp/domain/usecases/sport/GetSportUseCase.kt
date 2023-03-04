package com.example.sportfinderapp.domain.usecases.sport

import com.example.sportfinderapp.domain.repository.SportRepository
import com.example.sportfinderapp.domain.entity.Sport
import javax.inject.Inject

class GetSportUseCase(private val repository: SportRepository) {

    operator fun invoke(id: Int): Sport? {
        return repository.getSportUseCase(id)
    }
}
