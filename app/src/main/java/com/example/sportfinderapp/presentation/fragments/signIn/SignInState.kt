package com.example.sportfinderapp.presentation.fragments.signIn

sealed class SignInState {
    object EmptyEmail: SignInState()
    object EmptyPassword: SignInState()
    object Loading: SignInState()
    object Success: SignInState()
    object MailVerificationError: SignInState()
    object InvalidUserError: SignInState()
    object IncorrectPassword: SignInState()
    object NetworkError: SignInState()
    data class UnexpectedError(val message: String): SignInState()
}



