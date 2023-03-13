package com.example.sportfinderapp.presentation.fragments.registration

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportfinderapp.domain.entity.User
import javax.inject.Inject

class RegistrationViewModel @Inject constructor() : ViewModel() {

    private val _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean>
        get() = _errorInputEmail

    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean>
        get() = _errorInputPassword


    private val _errorInputFullName = MutableLiveData<Boolean>()
    val errorInputFullName: LiveData<Boolean>
        get() = _errorInputFullName

    private val _finishRegistration = MutableLiveData<Unit>()
    val finishRegistration: LiveData<Unit>
        get() = _finishRegistration

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user


    fun registration(inputFullName: String?, inputEmail: String?, inputPassword: String?) {
        val fullName = inputFullName ?: ""
        val email = inputEmail?.trim() ?: ""
        val password = inputPassword?.trim() ?: ""

        if (checkRegistration(fullName, email, password)) {
            createUser(fullName, email, password)
            finishWork()
        }

    }

    private fun createUser(fullName: String, email: String, password: String){
        _user.value = User(1, email, password, fullName) // create and get user in db
    }

    private fun checkRegistration(
        fullName: String,
        email: String,
        password: String
    ): Boolean {
        var result = true
        if (fullName.isBlank()) {
            _errorInputFullName.value = true
            result = false
        }
        if (email.isBlank() || !checkEmail(email)) {
            _errorInputEmail.value = true
            result = false
        }
        if (password.length < 6 || password.length > 60) {
            _errorInputPassword.value = true
            result = false
        }
        return result
    }

    private fun checkEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun resetErrorInputEmail() {
        _errorInputEmail.value = false
    }

    fun resetErrorInputPassword() {
        _errorInputPassword.value = false
    }

    fun resetErrorInputFullName() {
        _errorInputFullName.value = false
    }

    private fun finishWork() {
        _finishRegistration.value = Unit
    }

}