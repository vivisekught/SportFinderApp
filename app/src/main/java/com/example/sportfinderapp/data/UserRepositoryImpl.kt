package com.example.sportfinderapp.data

import com.example.sportfinderapp.domain.entity.Response
import com.example.sportfinderapp.domain.entity.User
import com.example.sportfinderapp.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    override suspend fun createUser(
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
}