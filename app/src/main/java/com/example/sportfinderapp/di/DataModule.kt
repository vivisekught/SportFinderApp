package com.example.sportfinderapp.di

import com.example.sportfinderapp.data.SportRepositoryImpl
import com.example.sportfinderapp.data.UserAuthRepositoryImpl
import com.example.sportfinderapp.domain.repository.SportRepository
import com.example.sportfinderapp.domain.repository.UserAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindSportRepository(impl: SportRepositoryImpl): SportRepository

    @ApplicationScope
    @Binds
    fun bindUserAuthRepository(impl: UserAuthRepositoryImpl): UserAuthRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth{
            return FirebaseAuth.getInstance()
        }
        @ApplicationScope
        @Provides
        fun provideFirebaseFirestore(): FirebaseFirestore{
            return FirebaseFirestore.getInstance()
        }
    }
}