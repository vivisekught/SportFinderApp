package com.example.sportfinderapp.presentation.fragments.signUp

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportfinderapp.domain.entity.responses.SignUpResponse
import com.example.sportfinderapp.domain.usecases.user.SignUpUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val singUpUserUseCase: SignUpUserUseCase
) : ViewModel() {

    private val _errorInputEmail = MutableLiveData<Boolean>()
    val errorInputEmail: LiveData<Boolean>
        get() = _errorInputEmail

    private val _errorInputPassword = MutableLiveData<Boolean>()
    val errorInputPassword: LiveData<Boolean>
        get() = _errorInputPassword

    private val _errorInputFullName = MutableLiveData<Boolean>()
    val errorInputFullName: LiveData<Boolean>
        get() = _errorInputFullName

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _singUpState = MutableLiveData<SignUpResponse>()
    val singUpState: LiveData<SignUpResponse> = _singUpState

    fun registration(inputFullName: String?, inputEmail: String?, inputPassword: String?) {
        val fullName = inputFullName ?: ""
        val email = inputEmail?.trim() ?: ""
        val password = inputPassword?.trim() ?: ""

        if (checkSignIn(fullName, email, password)) {
            viewModelScope.launch {
                singUpUserUseCase(email = email, password = password, fullName = fullName).collect{
                    when(it) {
                        is SignUpResponse.Loading -> {
                            _singUpState.value = SignUpResponse.Loading
                        }
                        is SignUpResponse.UnexpectedError -> {
                            _singUpState.value = SignUpResponse.UnexpectedError(it.message)
                        }
                        is SignUpResponse.Success -> {
                            _singUpState.value = SignUpResponse.Success
                        }
                        else -> {

                        }
                    }
                }
            }
        }

    }

    private fun checkSignIn(
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
}