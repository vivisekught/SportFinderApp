package com.example.sportfinderapp.di

import com.example.sportfinderapp.data.SportRepositoryImpl
import com.example.sportfinderapp.data.UserRepositoryImpl
import com.example.sportfinderapp.domain.repository.SportRepository
import com.example.sportfinderapp.domain.repository.UserRepository
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
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

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