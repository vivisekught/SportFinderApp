package com.example.sportfinderapp.presentation.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sportfinderapp.databinding.ActivityAuthBinding
import com.example.sportfinderapp.presentation.MainActivity
import com.example.sportfinderapp.presentation.SportAppFinderApp
import com.example.sportfinderapp.presentation.ViewModelFactory
import com.example.sportfinderapp.presentation.fragments.signIn.SignInFragment
import javax.inject.Inject


class AuthActivity : AppCompatActivity(), SignInFragment.StartMainActivity {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: AuthViewModel

    private lateinit var binding: ActivityAuthBinding

    private val component by lazy {
        (application as SportAppFinderApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AuthViewModel::class.java]
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setObservers()
    }

    private fun setObservers() {
        with(viewModel){
            emailVerified.observe(this@AuthActivity){
                startActivity(MainActivity.newInstance(this@AuthActivity))
                finish()
            }
        }
    }


    override fun startMainActivity() {
        startActivity(MainActivity.newInstance(this))
    }
}