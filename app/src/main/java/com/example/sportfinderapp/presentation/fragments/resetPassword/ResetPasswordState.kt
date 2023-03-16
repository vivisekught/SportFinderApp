package com.example.sportfinderapp.presentation.fragments.resetPassword

sealed class ResetPasswordState {
    object EmptyEmail: ResetPasswordState()
    object Loading: ResetPasswordState()
    object Success: ResetPasswordState()
    object IncorrectEmailError: ResetPasswordState()
    object EmailNotFound: ResetPasswordState()
    object NetworkError: ResetPasswordState()
    data class UnexpectedError(val message: String): ResetPasswordState()
}



