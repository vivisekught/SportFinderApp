package com.example.sportfinderapp.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sportfinderapp.R
import com.example.sportfinderapp.databinding.ActivityAuthBinding
import com.example.sportfinderapp.presentation.MainActivity
import com.example.sportfinderapp.presentation.fragments.signIn.SignInFragment
import com.google.firebase.auth.FirebaseAuth


class AuthActivity : AppCompatActivity(), SignInFragment.StartMainActivity {

    private lateinit var binding: ActivityAuthBinding

    var firebaseAuth = FirebaseAuth.getInstance()

//    private val component by lazy {
//        (this as SportAppFinderApp).component
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        component.inject(this)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        checkUserAuth()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun checkUserAuth(){
        if (firebaseAuth.currentUser?.isEmailVerified == true) {
            startActivity(MainActivity.newInstance(this))
        }
    }

    private fun checkIfAuth() {
        val currentUser = firebaseAuth.currentUser

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_auth)
//                    as NavHostFragment
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val mNavController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_auth) as NavHostFragment
        val navController = mNavController.navController

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_auth)
//                    as NavHostFragment
//        val navController = findNavController(R.id.nav_host_fragment_activity_auth)
        setupActionBarWithNavController(navController)


        if (currentUser?.isEmailVerified == false) {
//            navController.navigate(
//                SignUpFragmentDirections.actionSignUpFragmentToMailVerificationFragment()
//            )
        }

    }

    override fun startMainActivity() {
        startActivity(MainActivity.newInstance(this))
    }
}