package com.example.sportfinderapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.sportfinderapp.domain.entity.Sport

interface SportRepository {

    fun addSportUseCase(sport: Sport)

    fun getSportUseCase(id: Int): Sport?

    fun removeSportUseCase(id: Int)

    fun getSportList(coachId: Int): LiveData<List<Sport>>
}