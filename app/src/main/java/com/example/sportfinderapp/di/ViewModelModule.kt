package com.example.sportfinderapp.di

import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.presentation.auth.AuthViewModel
import com.example.sportfinderapp.presentation.fragments.home.HomeViewModel
import com.example.sportfinderapp.presentation.fragments.resetPassword.ResetPasswordViewModel
import com.example.sportfinderapp.presentation.fragments.signIn.SignInViewModel
import com.example.sportfinderapp.presentation.fragments.signUp.SignUpViewModel
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
    @ViewModelKey(SignInViewModel::class)
    fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    fun bindResetPasswordViewModel(viewModel: ResetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}