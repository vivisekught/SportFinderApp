package com.example.sportfinderapp.domain.usecases.user.auth

import com.example.sportfinderapp.domain.repository.UserAuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject  constructor(private val repository: UserAuthRepository) {
    suspend operator fun invoke(email: String) = repository.resetUserPassword(email = email)
}
