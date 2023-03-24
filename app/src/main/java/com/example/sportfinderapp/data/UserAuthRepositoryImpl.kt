package com.example.sportfinderapp.data

import com.example.sportfinderapp.domain.entity.responses.ResetPasswordResponse
import com.example.sportfinderapp.domain.entity.responses.SignInResponse
import com.example.sportfinderapp.domain.entity.responses.SignUpResponse
import com.example.sportfinderapp.domain.repository.UserAuthRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserAuthRepository {

    override suspend fun signUpUser(
        email: String,
        password: String,
        fullName: String
    ): Flow<SignUpResponse> = flow {
        var singUpSuccessfully = false
        emit(SignUpResponse.Loading)

        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    singUpSuccessfully = it.isSuccessful
                }.await()

            if (singUpSuccessfully) {

//                val userId = firebaseAuth.currentUser?.uid!!
//                val user = User(id = userId, email = email, fullName = fullName)
//
//                firebaseFirestore.collection("users").document(userId)
//                    .set(user).addOnSuccessListener { }.await()
                emit(SignUpResponse.Success)

            } else {
                emit(SignUpResponse.UnexpectedError("Failed to Sign Up"))
            }
        } catch (e: FirebaseAuthUserCollisionException) {
            emit(SignUpResponse.EmailAlreadyUsed)
        } catch (e: FirebaseNetworkException) {
            emit(SignUpResponse.NetworkError)
        } catch (e: FirebaseAuthWeakPasswordException) {
            emit(SignUpResponse.WeakPasswordError(e.reason.toString()))
        } catch (e: Exception) {
            emit(SignUpResponse.UnexpectedError(e.localizedMessage ?: "An Unexpected error"))
        }
    }

    override suspend fun signInUser(email: String, password: String): Flow<SignInResponse> = flow {
        var singInSuccessfully = false
        emit(SignInResponse.Loading)
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    singInSuccessfully = it.isSuccessful
                }.await()

            if (singInSuccessfully) {
                emit(SignInResponse.Success)
            } else {
                emit(SignInResponse.UnexpectedError("Failed to Sign Up"))
            }
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(SignInResponse.InvalidUserError)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(SignInResponse.InvalidCredentialError)
        } catch (e: FirebaseNetworkException) {
            emit(SignInResponse.NetworkError)
        } catch (e: FirebaseTooManyRequestsException) {
            emit(SignInResponse.UnexpectedError("Too many requests, please reset password or try later!"))
        } catch (e: Exception) {
            emit(
                SignInResponse.UnexpectedError(
                    e.localizedMessage?.toString() ?: "Unexpected error"
                )
            )
        }
    }

    override suspend fun resetUserPassword(email: String): Flow<ResetPasswordResponse> = flow {
        var resetPasswordSuccessfully = false
        try {
            emit(ResetPasswordResponse.Loading)
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    resetPasswordSuccessfully = it.isSuccessful
                }
            }.await()

            if (resetPasswordSuccessfully) {
                emit(ResetPasswordResponse.Success)
            } else {
                emit(ResetPasswordResponse.UnexpectedError("Failed to Sign Up"))
            }
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(ResetPasswordResponse.IncorrectEmailError)
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(ResetPasswordResponse.EmailNotFoundError)
        } catch (e: FirebaseNetworkException) {
            emit(ResetPasswordResponse.NetworkError)
        } catch (e: FirebaseTooManyRequestsException) {
            emit(ResetPasswordResponse.UnexpectedError("Too many requests, please reset password or try later!"))
        } catch (e: Exception) {
            emit(
                ResetPasswordResponse.UnexpectedError(
                    e.localizedMessage?.toString() ?: "Unexpected error"
                )
            )
        }
    }

    override suspend fun mailVerification() = flow {
        var result = true
        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
            result = it.isSuccessful
        }
        emit(result)
    }


}