package com.example.sportfinderapp.presentation.fragments.home

import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.domain.usecases.sport.GetSportListUseCase
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    getSportListUseCase: GetSportListUseCase

) : ViewModel() {


    val userSportsList = getSportListUseCase(1)

}