package com.example.sportfinderapp.domain.entity

sealed class Response{

    object Loading : Response()
    object Success: Response()
    data class Error(val message: String): Response()
}
