package com.example.sportfinderapp.di

import android.app.Application
import com.example.sportfinderapp.presentation.MainActivity
import com.example.sportfinderapp.presentation.fragments.home.HomeFragment
import com.example.sportfinderapp.presentation.fragments.login.LoginFragment
import com.example.sportfinderapp.presentation.fragments.sport.SportPageFragment
import com.example.sportfinderapp.presentation.fragments.training.TrainingPageFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: SportPageFragment)

    fun inject(fragment: TrainingPageFragment)

    fun inject(fragment: LoginFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}