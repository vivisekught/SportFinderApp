package com.example.sportfinderapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Int,
    var email: String,
    var password: String,
    var full_name: String,
    var age: Int? = null,
    var sex: String? = null,
) : Parcelable
