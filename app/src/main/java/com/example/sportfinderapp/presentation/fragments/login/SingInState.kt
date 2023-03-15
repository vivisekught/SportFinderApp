package com.example.sportfinderapp.presentation.fragments.login

sealed class SingInState {
    object EmptyEmail: SingInState()
    object EmptyPassword: SingInState()
    //object EmailNotFound: LoginState()
//object IncorrectPassword: LoginState()
    object Loading: SingInState()
    data class Error(val message: String): SingInState()
    object Success: SingInState()
}



