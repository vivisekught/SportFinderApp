package com.example.sportfinderapp.domain

import com.example.sportfinderapp.util.Days
import com.example.sportfinderapp.util.Level
import com.example.sportfinderapp.util.SportType
import java.sql.Time
import java.time.Duration
import java.util.Date

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
    )