package com.example.sportfinderapp.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth
) : ViewModel() {

    private var _emailVerified = MutableLiveData<Boolean>()
    val emailVerified: MutableLiveData<Boolean>
        get() = _emailVerified

    init {
        if (firebaseAuth.currentUser?.isEmailVerified == true) {
            _emailVerified.value = true
        }
    }
}