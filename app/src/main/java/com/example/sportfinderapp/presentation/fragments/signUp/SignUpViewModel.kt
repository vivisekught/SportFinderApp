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

    private val _state = MutableLiveData<SignUpState>()
    val state: LiveData<SignUpState>
        get() = _state

    fun registration(inputFullName: String?, inputEmail: String?, inputPassword: String?) {
        val fullName = inputFullName ?: ""
        val email = inputEmail?.trim() ?: ""
        val password = inputPassword?.trim() ?: ""

        if (checkSignUp(fullName, email, password)) {
            viewModelScope.launch {
                singUpUserUseCase(email = email, password = password, fullName = fullName).collect {
                    when (it) {
                        is SignUpResponse.Loading -> {
                            _state.value = SignUpState.Loading
                        }
                        is SignUpResponse.UnexpectedError -> {
                            _state.value = SignUpState.UnexpectedError(it.message)
                        }
                        is SignUpResponse.Success -> {
                            _state.value = SignUpState.Success
                        }
                        is SignUpResponse.EmailAlreadyUsed -> {
                            _state.value = SignUpState.EmailAlreadyUsedError
                        }
                        SignUpResponse.NetworkError -> {
                            _state.value = SignUpState.NetworkError
                        }
                        is SignUpResponse.WeakPasswordError -> {
                            _state.value = SignUpState.WeakPasswordError(it.reason)
                        }
                    }
                }
            }
        }
    }

    private fun checkSignUp(
        fullName: String,
        email: String,
        password: String
    ): Boolean {
        var result = true
        if (fullName.isBlank()) {
            _state.value = SignUpState.EmptyFullName
            result = false
        }
        if (email.isBlank() || !checkEmailPattern(email)) {
            _state.value = SignUpState.IncorrectEmail
            result = false
        }
        if (password.isBlank()) {
            _state.value = SignUpState.EmptyPassword
            result = false
        }
        return result
    }

    private fun checkEmailPattern(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}