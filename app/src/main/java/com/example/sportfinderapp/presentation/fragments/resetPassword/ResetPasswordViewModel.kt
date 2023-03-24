package com.example.sportfinderapp.presentation.fragments.resetPassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportfinderapp.domain.entity.responses.ResetPasswordResponse
import com.example.sportfinderapp.domain.usecases.user.auth.ResetPasswordUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ResetPasswordState>()
    val state: LiveData<ResetPasswordState>
        get() = _state

    fun resetPasswordCheck(inputEmail: String?){
        val email = inputEmail?.trim()
        if(email.isNullOrBlank()){
            _state.value = ResetPasswordState.EmptyEmail
            return
        } else {
            resetPassword(email)
        }
    }

    private fun resetPassword(email: String){
        viewModelScope.launch {
            resetPasswordUseCase(email = email).collect{
                when(it) {
                    ResetPasswordResponse.Loading -> {
                        _state.value = ResetPasswordState.Loading
                    }
                    ResetPasswordResponse.Success -> {
                        _state.value = ResetPasswordState.Success
                    }
                    ResetPasswordResponse.EmailNotFoundError -> {
                        _state.value = ResetPasswordState.EmailNotFound
                    }
                    ResetPasswordResponse.IncorrectEmailError -> {
                        _state.value = ResetPasswordState.IncorrectEmailError
                    }
                    ResetPasswordResponse.NetworkError -> {
                        _state.value = ResetPasswordState.NetworkError
                    }
                    is ResetPasswordResponse.UnexpectedError -> {
                        _state.value = ResetPasswordState.UnexpectedError(it.message)
                    }
                }
            }
        }
    }
}