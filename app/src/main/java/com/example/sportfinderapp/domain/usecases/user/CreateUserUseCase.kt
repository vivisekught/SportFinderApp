package com.example.sportfinderapp.domain.usecases.user

import com.example.sportfinderapp.domain.repository.UserRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(email: String, password: String, fullName: String) =
        repository.createUser(email = email, password = password, fullName = fullName)

}