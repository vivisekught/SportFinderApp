package com.example.sportfinderapp.presentation.fragments.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.domain.entity.User
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableLiveData<LoginState>()
    val state: LiveData<LoginState>
        get() = _state

    fun loginCheck(inputEmail: String?, inputPassword: String?){
        val email = inputEmail?.trim()
        val password = inputPassword?.trim()
        if(email.isNullOrBlank() && password.isNullOrBlank()){
            _state.value = EmptyEmailAndPassword
            return
        }
        if(email.isNullOrBlank()){
            _state.value = EmptyEmail
            return
        }
        if(password.isNullOrBlank()){
            _state.value = EmptyPassword
            return
        }
        if (!checkEmail(email)) {
            _state.value = EmailNotFound
            return
        }

        if (!checkPassword(email, password)) {
            _state.value = IncorrectPassword
            return
        }
        else {
            val user = getUserByEmail(email)
            _state.value = CorrectLoginData(user)
        }
    }

    private fun getUserByEmail(email: String): User {
        return User(1, "email", "1234", "Nikita", 14, "Man")
    }

    private fun checkEmail(email: String): Boolean{
        return true
        TODO("Check email in db")
    }
    private fun checkPassword(email: String, password: String): Boolean{
        return true
        TODO("Check password is an email password")
    }



}