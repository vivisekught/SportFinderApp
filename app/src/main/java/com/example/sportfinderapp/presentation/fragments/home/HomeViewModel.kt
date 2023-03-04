package com.example.sportfinderapp.presentation.fragments.home

import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.data.SportRepositoryImpl
import com.example.sportfinderapp.domain.usecases.sport.GetSportListUseCase

class HomeViewModel : ViewModel() {

    private val repository = SportRepositoryImpl()

    private val getSportListUseCase = GetSportListUseCase(repository)

    val userSportsList = getSportListUseCase(1)

}