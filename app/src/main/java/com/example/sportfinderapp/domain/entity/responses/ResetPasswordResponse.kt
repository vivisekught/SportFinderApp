package com.example.sportfinderapp.domain.entity.responses

sealed class ResetPasswordResponse{
    object Loading : ResetPasswordResponse()
    object Success: ResetPasswordResponse()
    object IncorrectEmailError: ResetPasswordResponse()
    object EmailNotFoundError: ResetPasswordResponse()
    object NetworkError: ResetPasswordResponse()
    data class UnexpectedError(val message: String): ResetPasswordResponse()
}
