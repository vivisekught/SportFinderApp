package com.example.sportfinderapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String,
    var email: String,
    var fullName: String,
    var age: Int? = null,
    var sex: String? = null,
) : Parcelable
