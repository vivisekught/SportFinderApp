package com.example.sportfinderapp.data

import com.example.sportfinderapp.domain.entity.User
import com.example.sportfinderapp.domain.entity.responses.SignInResponse
import com.example.sportfinderapp.domain.entity.responses.SignUpResponse
import com.example.sportfinderapp.domain.repository.UserRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    override suspend fun singUpUser(
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
                val userId = firebaseAuth.currentUser?.uid!!
                val user = User(id = userId, email = email, fullName = fullName)

                firebaseFirestore.collection("users").document(userId)
                    .set(user).addOnSuccessListener {

                    }.await()

                emit(SignUpResponse.Success)

            } else {
                emit(SignUpResponse.UnexpectedError("Failed to Sign Up"))
            }

        } catch (e: FirebaseNetworkException) {
            emit(SignUpResponse.NetworkError)
        } catch (e: FirebaseAuthWeakPasswordException) {
            emit(SignUpResponse.WeakPasswordError(e.reason.toString()))
        } catch (e: Exception) {
            emit(SignUpResponse.UnexpectedError(e.localizedMessage ?: "An Unexpected error"))
        }
    }

    override suspend fun singInUser(email: String, password: String): Flow<SignInResponse> = flow {
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
        }
        catch (e: FirebaseTooManyRequestsException){
            emit(SignInResponse.UnexpectedError("Too many requests, please reset password or try later!"))
        }catch (e: Exception) {
            emit(SignInResponse.UnexpectedError(e.localizedMessage?.toString() ?: "Unexpected error"))
        }


    }
}