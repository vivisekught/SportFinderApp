package com.example.sportfinderapp.data

import com.example.sportfinderapp.domain.entity.Response
import com.example.sportfinderapp.domain.entity.User
import com.example.sportfinderapp.domain.repository.UserRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
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
    ): Flow<Response> = flow {
        var singUpSuccessfully = false
        emit(Response.Loading)

        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    singUpSuccessfully = true
                }.await()

            if (singUpSuccessfully) {
                val userId = firebaseAuth.currentUser?.uid!!
                val user = User(id = userId, email = email, fullName = fullName)

                firebaseFirestore.collection("users").document(userId)
                    .set(user).addOnSuccessListener {

                    }.await()

                emit(Response.Success)

            } else {
                emit(Response.Error("Failed to Sing Up"))
            }

        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "An Unexpected error"))
        }
    }

    override suspend fun singInUser(email: String, password: String): Flow<Response> = flow {
        var singInSuccessfully = false
        emit(Response.Loading)
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    singInSuccessfully = it.isSuccessful
                }.await()

            if (singInSuccessfully) {
                emit(Response.Success)
            } else {
                emit(Response.Error("Failed to Sing Up"))
            }
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(Response.Error("Email does not exist"))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(Response.Error("IncorrectPassword"))
        } catch (e: FirebaseNetworkException) {
            emit(Response.Error("Network"))
        } catch (e: Exception){
            emit(Response.Error("An Unexpected error"))
        }


    }
}