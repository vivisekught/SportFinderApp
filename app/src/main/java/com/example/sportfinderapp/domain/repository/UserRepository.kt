package com.example.sportfinderapp.domain.repository

import com.example.sportfinderapp.domain.entity.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun createUser(email: String, password: String, fullName: String): Flow<Response>
}