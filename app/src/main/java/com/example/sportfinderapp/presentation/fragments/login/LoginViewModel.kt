package com.example.sportfinderapp.presentation.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportfinderapp.domain.entity.Response
import com.example.sportfinderapp.domain.usecases.user.SingInUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val singInUserUse: SingInUserUseCase
) : ViewModel() {
    private val _state = MutableLiveData<SingInState>()
    val state: LiveData<SingInState>
        get() = _state

    fun loginCheck(inputEmail: String?, inputPassword: String?){
        val email = inputEmail?.trim()
        val password = inputPassword?.trim()
        if(email.isNullOrBlank()){
            _state.value = SingInState.EmptyEmail
            return
        }
        if(password.isNullOrBlank()){
            _state.value = SingInState.EmptyPassword
            return
        }
        else {
            singIn(email, password)
        }
    }

    private fun singIn(email: String, password: String) {
        viewModelScope.launch {
            singInUserUse(email = email, password = password).collect{
                when(it) {
                    is Response.Loading -> {
                        _state.value = SingInState.Loading
                    }
                    is Response.Error -> {
                        _state.value = SingInState.Error(it.message)
                    }
                    is Response.Success -> {
                        _state.value = SingInState.Success
                    }
                }
            }
        }
    }
}