package com.example.sportfinderapp.presentation.fragments.login

import com.example.sportfinderapp.domain.entity.User

sealed class LoginState

object EmptyEmail: LoginState()
object EmptyPassword: LoginState()
object EmptyEmailAndPassword: LoginState()
object EmailNotFound: LoginState()
object IncorrectPassword: LoginState()
class CorrectLoginData(val user: User): LoginState()

