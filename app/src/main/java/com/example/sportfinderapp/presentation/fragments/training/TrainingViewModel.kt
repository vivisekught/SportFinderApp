package com.example.sportfinderapp.presentation.fragments.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.domain.Training

class TrainingViewModel : ViewModel() {
    var _training = MutableLiveData<Training>()
    val training: LiveData<Training>
        get() = _training
}