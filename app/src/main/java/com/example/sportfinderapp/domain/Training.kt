package com.example.sportfinderapp.domain

import com.example.sportfinderapp.util.Days
import com.example.sportfinderapp.util.Level
import com.example.sportfinderapp.util.SportType
import java.time.Duration
import java.util.Date

data class Training (
    val id: Int,
    var title: String,
    var description: String,
    var place: String,
    var days: ArrayList<Days>,
    var nearestDate: Date,
    var startedAt: Date,
    var finishedAt: Date,
    var duration: String,
    var level: Level,
    var type: SportType
    )