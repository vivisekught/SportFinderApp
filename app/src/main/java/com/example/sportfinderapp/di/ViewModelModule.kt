package com.example.sportfinderapp.di

import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.presentation.fragments.home.HomeViewModel
import com.example.sportfinderapp.presentation.fragments.login.LoginViewModel
import com.example.sportfinderapp.presentation.fragments.registration.RegistrationFragment
import com.example.sportfinderapp.presentation.fragments.registration.RegistrationViewModel
import com.example.sportfinderapp.presentation.fragments.sport.SportPageViewModel
import com.example.sportfinderapp.presentation.fragments.training.TrainingPageViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SportPageViewModel::class)
    fun bindSportPageViewModel(viewModel: SportPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrainingPageViewModel::class)
    fun bindTrainingPageViewModel(viewModel: TrainingPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

}