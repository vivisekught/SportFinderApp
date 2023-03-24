package com.example.sportfinderapp.domain.usecases.user.auth

import com.example.sportfinderapp.domain.repository.UserAuthRepository
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(private val repository: UserAuthRepository) {
    suspend operator fun invoke(email: String, password: String, fullName: String) =
        repository.signUpUser(email = email, password = password, fullName = fullName)
}