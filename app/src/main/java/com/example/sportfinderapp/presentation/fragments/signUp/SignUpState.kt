package com.example.sportfinderapp.presentation.fragments.signUp

sealed class SignUpState {
    object EmptyFullName: SignUpState()
    object EmptyPassword: SignUpState()
    object IncorrectEmail: SignUpState()
    object Loading: SignUpState()
    object Success: SignUpState()
    object EmailAlreadyUsedError: SignUpState()
    data class WeakPasswordError(val reason: String): SignUpState()
    object NetworkError: SignUpState()
    data class UnexpectedError(val message: String): SignUpState()
}

