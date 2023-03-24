package com.example.sportfinderapp.di

import android.app.Application
import com.example.sportfinderapp.presentation.MainActivity
import com.example.sportfinderapp.presentation.auth.AuthActivity
import com.example.sportfinderapp.presentation.fragments.home.HomeFragment
import com.example.sportfinderapp.presentation.fragments.resetPassword.ResetPasswordFragment
import com.example.sportfinderapp.presentation.fragments.signIn.SignInFragment
import com.example.sportfinderapp.presentation.fragments.signUp.SignUpFragment
import com.example.sportfinderapp.presentation.fragments.sport.SportPageFragment
import com.example.sportfinderapp.presentation.fragments.training.TrainingPageFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: AuthActivity)

    fun inject(fragment: HomeFragment)

    fun inject(fragment: SportPageFragment)

    fun inject(fragment: TrainingPageFragment)

    fun inject(fragment: SignInFragment)

    fun inject(fragment: SignUpFragment)

    fun inject(fragment: ResetPasswordFragment)


    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application,
        ): ApplicationComponent
    }
}