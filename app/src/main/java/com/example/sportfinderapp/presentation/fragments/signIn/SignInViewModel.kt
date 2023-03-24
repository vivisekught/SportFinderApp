package com.example.sportfinderapp.presentation.fragments.signIn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportfinderapp.domain.entity.responses.SignInResponse
import com.example.sportfinderapp.domain.usecases.user.auth.MailVerificationUseCase
import com.example.sportfinderapp.domain.usecases.user.auth.SignInUserUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val singInUserUse: SignInUserUseCase,
    private val mailVerificationUseCase: MailVerificationUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    private val _state = MutableLiveData<SignInState>()
    val state: LiveData<SignInState>
        get() = _state

    fun loginCheck(inputEmail: String?, inputPassword: String?){
        val email = inputEmail?.trim()
        val password = inputPassword?.trim()
        if(email.isNullOrBlank()){
            _state.value = SignInState.EmptyEmail
            return
        }
        if(password.isNullOrBlank()){
            _state.value = SignInState.EmptyPassword
            return
        }
        else {
            singIn(email, password)
        }
    }

    private fun singIn(email: String, password: String) {
        viewModelScope.launch {
            singInUserUse(email = email, password = password).collect{
                Log.d("Nikita", it.toString())
                when(it) {
                    is SignInResponse.Loading -> {
                        _state.value = SignInState.Loading
                    }
                    is SignInResponse.Success -> {
                        checkMailVerification()
                    }
                    is SignInResponse.InvalidUserError -> {
                        _state.value = SignInState.InvalidUserError
                    }
                    is SignInResponse.InvalidCredentialError -> {
                        _state.value = SignInState.IncorrectPassword
                    }
                    SignInResponse.NetworkError -> {
                        _state.value = SignInState.NetworkError
                    }
                    is SignInResponse.UnexpectedError -> {
                        _state.value = SignInState.UnexpectedError(it.message)
                    }
                }
            }
        }
    }

    private fun checkMailVerification(){
        if(firebaseAuth.currentUser?.isEmailVerified == true){
            _state.value = SignInState.Success
        }
        else {
            _state.value = SignInState.MailVerificationError
        }
    }

    fun sendMailVerification(){
        viewModelScope.launch {
            mailVerificationUseCase().collect{
                if(it){
                    Log.d("Nikita", "Success")
                } else {
                    Log.d("Nikita", "Failure")
                }
            }
        }
    }
}