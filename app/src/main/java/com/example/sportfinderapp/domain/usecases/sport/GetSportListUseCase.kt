package com.example.sportfinderapp.domain.usecases.sport

import androidx.lifecycle.LiveData
import com.example.sportfinderapp.domain.repository.SportRepository
import com.example.sportfinderapp.domain.entity.Sport
import javax.inject.Inject

class GetSportListUseCase(private val repository: SportRepository) {

    operator fun invoke(coachId: Int): LiveData<List<Sport>>{
        return repository.getSportList(coachId)
    }
}