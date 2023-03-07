package com.example.sportfinderapp.domain.entity

import com.example.sportfinderapp.util.SportType
import android.os.Parcelable
import com.example.sportfinderapp.util.Level
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport (
    var id: Int,
    var title: String,
    var description: String,
    var place: String,
    var duration: String,
    var type: SportType,
    val level: Level,
    var coachId: Int,
    var coachName: String
    ): Parcelable