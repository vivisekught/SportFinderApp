package com.example.sportfinderapp.domain.entity.responses

sealed class SignUpResponse{

    object Loading : SignUpResponse()
    object Success: SignUpResponse()
    object EmailAlreadyUsed: SignUpResponse()
    data class WeakPasswordError(val reason: String): SignUpResponse()
    object NetworkError: SignUpResponse()
    data class UnexpectedError(val message: String): SignUpResponse()
}
