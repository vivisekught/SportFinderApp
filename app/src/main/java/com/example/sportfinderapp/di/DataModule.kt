package com.example.sportfinderapp.di

import com.example.sportfinderapp.data.SportRepositoryImpl
import com.example.sportfinderapp.domain.repository.SportRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindSportRepository(impl: SportRepositoryImpl): SportRepository
}