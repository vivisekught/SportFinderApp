package com.example.sportfinderapp.domain.entity

import com.example.sportfinderapp.util.Level
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Training(
    var id: Int,
    var sportId: Int,
    var date: String,
    var time: String,
    var level: Level,
    var totalPlaces: Int,
    var availablePlaces: Int,
    var bookingPlaces: Int
    ): Parcelable
