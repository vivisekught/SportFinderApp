package com.example.sportfinderapp.domain.entity.responses

sealed class SignInResponse{
    object Loading : SignInResponse()
    object Success: SignInResponse()
    object InvalidUserError: SignInResponse()
    object InvalidCredentialError: SignInResponse()
    object NetworkError: SignInResponse()
    data class UnexpectedError(val message: String): SignInResponse()
}
