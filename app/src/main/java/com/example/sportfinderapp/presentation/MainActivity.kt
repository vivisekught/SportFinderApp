package com.example.sportfinderapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TrainingFragment", this.toString())
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("TrainingFragment", supportActionBar.toString())
        setContentView(binding.root)
        setupBottomNav()
    }

    private fun setupBottomNav(){
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_chat
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    override fun updateToolbarTitle(trainingTitle: String) {
//        Log.d("TrainingFragment", supportActionBar.toString())
//        supportActionBar?.title = trainingTitle
//        Log.d("TrainingFragment", supportActionBar.toString())
//    }
}