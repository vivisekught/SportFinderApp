package com.example.sportfinderapp.domain.entity

data class User(
    var id: Int,
    var email: String,
    var password: String,
    var full_name: String,
    var age: Int,
    var sex: String,
)
