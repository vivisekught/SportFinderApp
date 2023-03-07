package com.example.sportfinderapp.presentation

import android.app.Application
import com.example.sportfinderapp.di.DaggerApplicationComponent

class SportAppFinderApp: Application() {

    val component = DaggerApplicationComponent.factory().create(this)

}