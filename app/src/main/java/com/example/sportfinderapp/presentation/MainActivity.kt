package com.example.sportfinderapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sportfinderapp.R
import com.example.sportfinderapp.data.SportRepositoryImpl
import com.example.sportfinderapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repositoryImpl: SportRepositoryImpl

    private val component by lazy {
        (application as SportAppFinderApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        component.inject(this)
        super.onCreate(savedInstanceState)
        Log.d("Nikita", repositoryImpl.toString())
        binding = ActivityMainBinding.inflate(layoutInflater)
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