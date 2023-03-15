package com.example.sportfinderapp.domain.usecases.user

import com.example.sportfinderapp.domain.repository.UserRepository
import javax.inject.Inject

class SignInUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(email: String, password: String) =
        repository.singInUser(email = email, password = password)
}