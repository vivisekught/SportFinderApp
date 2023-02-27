package com.example.sportfinderapp.domain

import android.os.Parcelable
import com.example.sportfinderapp.util.Level
import com.example.sportfinderapp.util.SportType
import kotlinx.parcelize.Parcelize

@Parcelize
data class Training (
    var id: Int,
    var title: String,
    var description: String,
    var place: String,
    var monday: String?,
    var tuesday: String?,
    var wednesday: String?,
    var thursday: String?,
    var friday: String?,
    var saturday: String?,
    var sunday: String?,
    var duration: String,
    var level: Level,
    var type: SportType,
    var coachId: Int,
    var coachName: String
    ): Parcelable