package com.example.sportfinderapp.domain.repository

import com.example.sportfinderapp.domain.entity.responses.ResetPasswordResponse
import com.example.sportfinderapp.domain.entity.responses.SignInResponse
import com.example.sportfinderapp.domain.entity.responses.SignUpResponse
import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {

    suspend fun signUpUser(email: String, password: String, fullName: String): Flow<SignUpResponse>

    suspend fun signInUser(email: String, password: String): Flow<SignInResponse>

    suspend fun resetUserPassword(email: String): Flow<ResetPasswordResponse>

    suspend fun mailVerification(): Flow<Boolean>
}